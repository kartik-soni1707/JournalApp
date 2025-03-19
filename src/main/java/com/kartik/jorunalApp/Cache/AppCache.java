package com.kartik.jorunalApp.Cache;

import com.kartik.jorunalApp.entity.ConfigJournalEntity;
import com.kartik.jorunalApp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalAppRepo crepo;
    public Map<String, String> appCache= new HashMap<>();
    @PostConstruct
    public void init(){
        List<ConfigJournalEntity> all =crepo.findAll();
        for (ConfigJournalEntity a: all){
            appCache.put(a.getKey(),a.getValue());
        }
    }
}
