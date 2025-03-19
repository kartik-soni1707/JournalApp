package com.kartik.jorunalApp.repository;

import com.kartik.jorunalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoCriteria {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<UserEntity> getUsersForSA(){
        Query q = new Query();
        q.addCriteria(Criteria.where("email").regex("^(?!.*\\.\\.)([a-zA-Z0-9._%+-]+)@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")); 
        q.addCriteria(Criteria.where("sentiment").is(true));
        List<UserEntity> users=mongoTemplate.find(q, UserEntity.class);
        return  users;
    }
}
