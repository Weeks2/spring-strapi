package com.weeks2.strapi.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LessonPayload {
    @JsonProperty("data")
    Lesson.Attributes data;
}
