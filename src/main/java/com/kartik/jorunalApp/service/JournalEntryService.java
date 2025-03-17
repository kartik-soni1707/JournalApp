package com.kartik.jorunalApp.service;

import com.kartik.jorunalApp.entity.JournalEntity;
import com.kartik.jorunalApp.entity.UserEntity;
import com.kartik.jorunalApp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo jRepo;

    @Autowired
    private UserEntryService uServe;

    @Transactional
    public void save(JournalEntity j, String user){
        try{
        UserEntity u = uServe.findByName(user);
        JournalEntity jE=jRepo.save(j);
        u.getEntries().add(jE);
        uServe.saveUser(u);}
        catch (Exception e){
            log.error("Error :",e);
            throw e;
        }
    }
    public void save(JournalEntity j){
        JournalEntity jE=jRepo.save(j);
    }
    public List<JournalEntity> getAll(){
        return jRepo.findAll();
    }
    public Optional<JournalEntity> findByiD(ObjectId id){
        return jRepo.findById(id);
    }
    @Transactional
    public boolean removID(ObjectId id, String user){
        boolean removed=false;
        try{
        UserEntity u = uServe.findByName(user);
        removed=u.getEntries().removeIf(x->x.getId().equals(id));
        if(removed){
        jRepo.deleteById(id);
        uServe.saveUser(u);}
        }
        catch (Exception e){
            System.out.println(e);
        }
        return removed;
    }


}
