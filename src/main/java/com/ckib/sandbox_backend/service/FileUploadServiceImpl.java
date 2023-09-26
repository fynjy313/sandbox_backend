package com.ckib.sandbox_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (System.getProperty("os.name").startsWith("Windows")) {
            file.transferTo(new File("C:\\upload\\" + fileName));
        } else {
            file.transferTo(new File("/opt/app/upload/" + fileName));
        }
    }
}
