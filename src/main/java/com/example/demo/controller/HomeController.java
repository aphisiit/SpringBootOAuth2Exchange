package com.example.demo.controller;

import com.example.demo.service.TokenService;
import flexjson.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenService tokenService;

    @GetMapping("/")
    public ResponseEntity<String> home(){

        LOGGER.info("home()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8;");
        Map<String,String> map = new HashMap<>();
        map.put("JTW","http://localhost:8081/jwt");
        map.put("JTW2","http://localhost:8081/jwt2");
        try {
            return new ResponseEntity<>(new JSONSerializer().prettyPrint(true).serialize(map),headers,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR",headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jwt")
    public ResponseEntity<String> jwt(){

        LOGGER.info("jwt()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8;");
        try {
            return tokenService.getTokenJWT();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jwt2")
    public ResponseEntity<String> jwt2(){

        LOGGER.info("jwt2()");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json; charset=utf-8;");
        try {
            return tokenService.getTokenJWT2();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),headers,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
