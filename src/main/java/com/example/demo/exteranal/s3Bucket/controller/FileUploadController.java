package com.example.demo.exteranal.s3Bucket.controller;

import com.example.demo.exteranal.s3Bucket.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Tag(name = "사진 업로드 관리")
@RestController
@RequestMapping("/api/server")
public class FileUploadController {

    private final S3Service s3UploadService;

    @Autowired
    public FileUploadController(S3Service s3UploadService) {
        this.s3UploadService = s3UploadService;
    }
    @Operation(summary = "단일 사진 업로드", description = "1개만")
    @PostMapping("/upload/file")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3UploadService.saveFile(file);
            return ResponseEntity.ok("File uploaded successfully. URL: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload the file: " + e.getMessage());
        }
    }
    @Operation(summary = "다중 사진 업로드", description = "1개만 올려도 가능")
    @PostMapping("/upload/files")
    public ResponseEntity<String> handleFilesUpload(@RequestPart("files") List<MultipartFile> files) {
        try {
            List<String> fileUrls = s3UploadService.saveFileList(files);
            // 여기서 fileUrls를 활용하여 필요한 응답 형태로 만들어 반환할 수 있습니다.
            return ResponseEntity.ok("Files uploaded successfully. URLs: " + fileUrls);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload the files: " + e.getMessage());
        }
    }
    @Operation(summary = "다중 사진 삭제", description = "1개만 올려도 가능")
    @DeleteMapping("/delete/files")
    public ResponseEntity<String> handleFilesDelete(List<String> originalFilenameLsit) {
        try {
            for(String filename : originalFilenameLsit){
                s3UploadService.deleteImage(filename);
            }
            return ResponseEntity.ok("Files deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload the files: " + e.getMessage());
        }
    }
    @Operation(summary = "단일 사진 다운로드", description = "1개만 사용 가능")
    @GetMapping("/download/file")
    public ResponseEntity<UrlResource> handleFilesDownload(String originalFilename) {
        return s3UploadService.downloadImage(originalFilename);
    }
}
