package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
public class RandomConfig {

    @Bean
    Random random() {
        return new Random();
    }

    @Bean
    Map<String, Integer> userMap() {
        Map<String, Integer> userMap = new HashMap<String, Integer>();

        userMap.put("admin", 1000);
        return userMap;
    }


}
