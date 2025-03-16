package com.kartik.jorunalApp.controller;

import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Ok";
    }
    @PostMapping("/create-user")
    public ResponseEntity<UserEntity> add(@RequestBody UserEntity user){
        userEntryService.saveNewUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
