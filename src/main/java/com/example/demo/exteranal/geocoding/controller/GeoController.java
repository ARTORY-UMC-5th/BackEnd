//package com.example.demo.exteranal.geocoding.controller;
//
//import com.example.demo.exteranal.geocoding.dto.GeocodingResponseDto;
//import com.example.demo.exteranal.geocoding.service.GeocodingUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/test")
//@RequiredArgsConstructor
//public class GeoController {
//
//    private final GeocodingUtil geocodingUtil;
//
//    @GetMapping("/geocoding/{address}")
//    @ResponseBody
//    public GeocodingResponseDto test(@PathVariable("address") String address) throws IOException {
//        return geocodingUtil.getLocationByAddress(address);
//    }
//
//}
