package com.kartik.jorunalApp.repository;

import com.kartik.jorunalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository <JournalEntity, ObjectId>{
}
