//package com.example.service;
//
//import com.example.entity.Activity;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.server.ResponseStatusException;
//
//@Service
//public class ActivityService {
//
//    @Value("${external.boredapi.activity}")
//    private String activityEndpoint;
//    private final RestTemplate restTemplate;
//
//    public ActivityService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public Activity getRandomActivity() {
//        ResponseEntity<Activity> responseEntity = restTemplate.getForEntity(activityEndpoint, Activity.class);
//        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
//            throw new ResponseStatusException(responseEntity.getStatusCode());
//        }
//        return responseEntity.getBody();
//
//    }
//}
