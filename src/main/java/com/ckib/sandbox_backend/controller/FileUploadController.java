package com.ckib.sandbox_backend.controller;

import com.ckib.sandbox_backend.service.FileUploadServiceImpl;
import com.ckib.sandbox_backend.service.PipelineServiceImpl;
import org.gitlab4j.api.GitLabApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {
    @Autowired
    private PipelineServiceImpl pipelineService;
    @Autowired
    FileUploadServiceImpl fileUploadService;
//TODO: curl http://localhost:8080/health
//{"status":"UP","diskSpace":{"status":"UP","total":981190307840,"free":744776503296,"threshold":10485760}}
    @GetMapping("/")
    public String index(Model model) {
        try {
            model.addAttribute("statistic", pipelineService.getStatistic());
        } catch (GitLabApiException e) {
            throw new RuntimeException(e);
        }
        return "uploader";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadService.uploadFile(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully");
    }
}
