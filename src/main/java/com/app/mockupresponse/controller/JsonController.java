package com.app.mockupresponse.controller;

import com.app.mockupresponse.Service.MongoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class JsonController {

    @Autowired
    private MongoService mongoService;

    @GetMapping(
    		value = {
                    "/test",
                    "/api/v2/**"},
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getResponse(
            HttpServletRequest httpRequest
    ){
    	log.info(httpRequest.getRequestURI());
    	log.info(httpRequest.getRequestURL().toString());
        return mongoService.getJsonResponse(httpRequest.getRequestURI());
    }
    
    @PostMapping(
    		value = {
                    "/api/v2/**"},
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postResponse(
            HttpServletRequest httpRequest
    ){
    	log.info(httpRequest.getRequestURI());
    	log.info(httpRequest.getRequestURL().toString());
        return mongoService.getJsonResponse(httpRequest.getRequestURI());
    }
    
    @RequestMapping(path = "/api/v2/logistics/download_shipping_document", method = RequestMethod.POST)
    public ResponseEntity<Resource> download(String param) throws IOException {

//    	String SERVER_LOCATION = "D:\\";
//    	String image = "";
//    	String EXTENSION = "";
    	
    	File file = new File("D:\\download_shipping_document rubi shopee.pdf");

        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");


        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}