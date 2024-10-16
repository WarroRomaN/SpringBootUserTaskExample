package com.example.controller;

import com.example.service.SpinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpinController {

    SpinService spinService;

    public SpinController(SpinService spinService) {
        this.spinService = spinService;
    }

    @GetMapping("/spin/{userId}/{bet}")
    int spin(@PathVariable String userId, @PathVariable int bet) {
        return spinService.spin(userId, bet);
    }
}
