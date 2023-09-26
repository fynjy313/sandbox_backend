package com.ckib.sandbox_backend.controller;

import com.ckib.sandbox_backend.service.TriggerPipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TriggerPipelineController {
    @Autowired
    TriggerPipelineService triggerPipelineService;

    @PostMapping("/startScan")
    public ResponseEntity<?> startScan(@RequestParam String imageName) {
        //String imageName = "harbor.stage.local/ssdlc/yandex-cloud-devsecops@sha256:2e78f63ae7b0be9e5d0dbe409bc6e647978d68fad5d5e601b5764167f96db47d";
        try {
            triggerPipelineService.triggerPipeline(imageName);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("Image scan started successfully");
    }
}
