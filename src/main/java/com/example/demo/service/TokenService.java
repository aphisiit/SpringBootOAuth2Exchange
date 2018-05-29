package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface TokenService {
    ResponseEntity<String> getTokenJWT();
    ResponseEntity<String> getTokenJWT2();
}
