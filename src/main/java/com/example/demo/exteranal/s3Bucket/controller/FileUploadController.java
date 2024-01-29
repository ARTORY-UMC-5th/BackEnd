package com.example.demo.exteranal.s3Bucket.controller;

import com.example.demo.exteranal.s3Bucket.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @PostMapping("/server/uploads")
    public ResponseEntity<String> handleFilesUpload(@RequestPart("files") List<MultipartFile> files) {
        try {
            List<String> fileUrls = s3UploadService.saveFileList(files);
            // 여기서 fileUrls를 활용하여 필요한 응답 형태로 만들어 반환할 수 있습니다.
            return ResponseEntity.ok("Files uploaded successfully. URLs: " + fileUrls);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload the files: " + e.getMessage());
        }
    }
}
