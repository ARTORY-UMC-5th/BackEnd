package com.example.demo.storyTest;

import com.example.demo.domain.story.controller.StoryController;
import com.example.demo.domain.story.dto.StoryResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class StoryTest {

    @Autowired
    private StoryController storyController;

    @Test
    public void StoryTest(){

        ResponseEntity<StoryResponseDto.StorySpecificResponseDto> storyDto = storyController.getStory(1L);
        StoryResponseDto.StorySpecificResponseDto temp = storyDto.getBody();



        Assertions.assertNotNull(temp);
    }
}
