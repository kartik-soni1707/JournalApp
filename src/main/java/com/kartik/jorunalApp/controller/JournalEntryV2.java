package com.kartik.jorunalApp.controller;

import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.service.JournalEntryService;
import com.kartik.jorunalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.kartik.jorunalApp.entity.JournalEntity;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryV2 {

    @Autowired
    JournalEntryService jS;
    @Autowired
    UserEntryService uS;

    @GetMapping
    public ResponseEntity<?>getMany(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String username = a.getName();
        System.out.println(username);
        UserEntity u = uS.findByName(username);
        if (u!=null) {
            List<JournalEntity> all = u.getEntries();
            if (all != null && !all.isEmpty()) {
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<JournalEntity> createMapping(@RequestBody JournalEntity j){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String username = a.getName();
        j.setDate(LocalDateTime.now());
        jS.save(j,username);
        return new ResponseEntity<JournalEntity>(j, HttpStatus.CREATED);
    }
    @GetMapping("id/{my_var}")
    public ResponseEntity<JournalEntity> getOne(@PathVariable ObjectId my_var){

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String user = a.getName();
        UserEntity u = uS.findByName(user);

        List<JournalEntity> collect = u.getEntries().stream().filter(x -> x.getId().equals(my_var)).collect(Collectors.toList());

        if (!collect.isEmpty()){
            Optional<JournalEntity> journalEntry = jS.findByiD(my_var);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

            return new ResponseEntity<JournalEntity>( HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{my_var}")
    public ResponseEntity<JournalEntity> deelteONe(@PathVariable ObjectId my_var){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String user = a.getName();
         boolean b=jS.removID(my_var,user);
         if (b==true){
             return new ResponseEntity(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{my_var}")
    public ResponseEntity<JournalEntity> updateStuff(@PathVariable ObjectId my_var, @RequestBody JournalEntity j){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        String user = a.getName();
        JournalEntity old= jS.findByiD(my_var).orElse(null);
        if (old!=null){
            old.setTitle(j.getTitle());
            old.setContent(j.getContent());
        }
        jS.save(old);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
