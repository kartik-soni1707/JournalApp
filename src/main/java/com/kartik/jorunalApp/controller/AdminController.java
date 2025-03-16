package com.kartik.jorunalApp.controller;

import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserEntryService us;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAll(){
        List<UserEntity> all=us.getAll();
        if (all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public ResponseEntity<?> create_admin(@RequestBody UserEntity u){
        us.saveNewAdmin(u);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
