package com.example.demo.exteranal.s3Bucket.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
    public List<String> saveFileList(List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            String originalFilename = multipartFile.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
            String fileUrl = amazonS3.getUrl(bucket, originalFilename).toString();
            fileUrls.add(fileUrl);
        }

        return fileUrls;
    }
    public ResponseEntity<UrlResource> downloadImage(String originalFilename) {
        UrlResource urlResource = new UrlResource(amazonS3.getUrl(bucket, originalFilename));

        String contentDisposition = "attachment; filename=\"" +  originalFilename + "\"";

        // header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
    public void deleteImage(String originalFilename)  {
        amazonS3.deleteObject(bucket, originalFilename);
    }
}