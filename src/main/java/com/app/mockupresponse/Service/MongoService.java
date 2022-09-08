package com.app.mockupresponse.Service;

import com.app.mockupresponse.model.document.JsonMockupDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MongoService {

    @Autowired
    public MongoTemplate mongoTemplate;

    public ResponseEntity<String> getJsonResponse(String path){
    	log.error(path);
        Query query = new Query();
        query.addCriteria(Criteria.where("path").is(path));
        JsonMockupDto respDto =  mongoTemplate.findOne(query, JsonMockupDto.class, JsonMockupDto.COLLECTION);
        log.error(respDto.toString());
        log.error("id: " + respDto.getId().toString());
        log.error("body: " + respDto.getJsonBody());
        return new ResponseEntity<>(respDto.getJsonBody(), HttpStatus.OK);
//        return null;
    }
}
