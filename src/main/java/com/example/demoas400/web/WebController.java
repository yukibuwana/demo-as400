package com.example.demoas400.web;

import com.example.demoas400.config.AS400Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class WebController {
    @Autowired
    AS400Config as400Config;

    @GetMapping("/test")
    public ResponseEntity<List<String>> getTest() {

        final List<String> result = as400Config.test();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/connect")
    public ResponseEntity<List<String>> getConnect() {

        final List<String> result = as400Config.connect();
        return ResponseEntity.ok().body(result);
    }
}
