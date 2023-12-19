package com.weeks2.strapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.weeks2.strapi.common.ClientRest;
import com.weeks2.strapi.lesson.Lesson;
import com.weeks2.strapi.lesson.LessonData;
import com.weeks2.strapi.lesson.LessonList;
import com.weeks2.strapi.lesson.LessonPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LessonService {
    @Value("${api.lesson}")
    private String url;
    @Autowired
    private ClientRest rest;

    public List<Lesson.Attributes> fetch(HttpHeaders authHeader) {
        return rest.httpGetRequest(url, authHeader, LessonList.class)
                .getLessons().stream()
                .map(this::toLessonWithId)
                .collect(Collectors.toList());
    }

    private Lesson.Attributes toLessonWithId(Lesson lesson) {
        var l = lesson.getAttributes();
        l.setId(lesson.getId());
        return l;
    }

    public void create(HttpHeaders headers,Lesson.Attributes data) {
        var payload = new LessonPayload();
        payload.setData(data);
        var response = rest.httpPostRequest(url, headers,payload,LessonData.class);
        log.info("response {}",response);
    }

    public List<Lesson.Attributes> findById(HttpHeaders authHeader,int id) {
       return fetch(authHeader).stream()
               .filter(l-> l.getId() == id)
               .collect(Collectors.toList());
    }

    public List<com.weeks2.strapi.model.Lesson> fetchData(HttpHeaders headers, int id) {
        return rest.httpGetRequest(url, headers, ClientRest.DataList.class)
                .getData()
                .stream()
                .map(jsonNode -> toLesson(jsonNode))
                .collect(Collectors.toList());
    }

    private com.weeks2.strapi.model.Lesson toLesson(JsonNode item) {
        try {
            var lesson = rest.mapNode(item, com.weeks2.strapi.model.Lesson.class);
            lesson.setId(item.get("id").asInt());
            return lesson;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
