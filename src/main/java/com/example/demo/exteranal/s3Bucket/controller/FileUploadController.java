package com.example.demo.exteranal.s3Bucket.controller;

import com.example.demo.exteranal.s3Bucket.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class FileUploadController {

    private final S3Service s3UploadService;

    @Autowired
    public FileUploadController(S3Service s3UploadService) {
        this.s3UploadService = s3UploadService;
    }

    @PostMapping("/server/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3UploadService.saveFile(file);
            return ResponseEntity.ok("File uploaded successfully. URL: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }
    }
}
