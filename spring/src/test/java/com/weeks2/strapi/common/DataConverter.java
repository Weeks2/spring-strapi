package com.weeks2.strapi.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.weeks2.strapi.local.AuthRequest;
import com.weeks2.strapi.local.AuthResponse;
import com.weeks2.strapi.model.Lesson;
import com.weeks2.strapi.service.StrapiAuthService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class DataConverter {
    @Autowired
    ClientRest clientRest;

    @Autowired
    StrapiAuthService strapiAuthService;

    @Value("${api.lesson}")
    private String url;


    private ResponseEntity<AuthResponse> token;

    @BeforeEach
    void init() {
        token = strapiAuthService.auth(new AuthRequest("ch.ibarrac@gmail.com",
                "91b4d142823f7d20c5f08df69122de43f35f057a988d9619f6d3138485c9a203")
        );
    }

    @Test
    void testResponse()  {
        var data = clientRest.httpGetRequest(url,createHeaders(), ClientRest.DataList.class)
                .getData()
                .stream()
                .map(jsonNode -> toLesson(jsonNode))
                .collect(Collectors.toList());

        log.info("{}",data);

    }

    private Lesson toLesson(JsonNode item) {
        try {
            var lesson = clientRest.mapNode(item, Lesson.class);
            lesson.setId(item.get("id").asInt());
            return lesson;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpHeaders createHeaders() {
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ token.getBody().getJwt());
        headers.set("Content-Type", "application/json");
        return headers;
    }
}
