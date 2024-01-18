package com.example.demo.domain.story.controller;

import com.example.demo.domain.story.converter.StoryConverter;
import com.example.demo.domain.story.dto.StoryRequestDto;
import com.example.demo.domain.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryService storyService;
    private final StoryConverter storyConverter;



    @PostMapping("/save")
    public ResponseEntity<String> saveStory(@RequestBody StoryRequestDto storyRequestDto) {
        try {
            storyService.saveStory(storyRequestDto);
            return ResponseEntity.ok("Story saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the story.");
        }
    }


}