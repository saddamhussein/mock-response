package com.app.mockupresponse.model.document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Setter @Getter
@Document(JsonMockupDto.COLLECTION)
public class JsonMockupDto {

    @JsonIgnore
    public static final String COLLECTION = "json_mockup";

    @Id
    private String id;

    private String path;

    private String jsonBody;
}
