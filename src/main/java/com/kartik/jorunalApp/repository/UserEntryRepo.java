package com.kartik.jorunalApp.repository;

import com.kartik.jorunalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository <UserEntity, ObjectId>{
    UserEntity findByname(String name);
}
