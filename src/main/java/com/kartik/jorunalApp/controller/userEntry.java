package com.kartik.jorunalApp.controller;

import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userEntry {
    @Autowired
    private UserEntryService userEntryService;


    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserEntity user){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String name = a.getName();
        UserEntity u = userEntryService.findByName(name);
        if (u!=null){
            u.setName(user.getName());
            u.setPassword(user.getPassword());
            userEntryService.saveNewUser(u);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody UserEntity user){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String name = a.getName();
        UserEntity u = userEntryService.findByName(name);
        userEntryService.removID(u.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
