package com.kartik.jorunalApp.service;

import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepo uRepo;
    private  static  final Logger logger= LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    private PasswordEncoder p;
    public void saveNewUser(UserEntity j){
        try{
        j.setPassword(p.encode(j.getPassword()));
        j.setRoles(Arrays.asList("USER"));
        uRepo.save(j);

        }
        catch (Exception e){
            logger.info("hahahaah");
            logger.warn("hahahaah");
            logger.error("hahahaah");

        }
    }
    public void saveNewAdmin(UserEntity j){
        j.setPassword(p.encode(j.getPassword()));
        j.setRoles(Arrays.asList("ADMIN"));
        uRepo.save(j);
    }
    public void saveUser(UserEntity j){

        uRepo.save(j);
    }
    public List<UserEntity> getAll(){
        return uRepo.findAll();
    }
    public Optional<UserEntity> findByiD(ObjectId id){
        return uRepo.findById(id);
    }
    public void removID(ObjectId id){
        uRepo.deleteById(id);
    }
    public UserEntity findByName(String name){
        return uRepo.findByname(name);
    }

}
