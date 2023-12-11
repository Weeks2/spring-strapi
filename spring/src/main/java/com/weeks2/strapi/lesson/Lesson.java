package com.weeks2.strapi.lesson;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weeks2.strapi.config.StrapiConstants;
import lombok.Data;

import java.util.Date;
@Data
public class Lesson {
    @JsonProperty("id")
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Attributes attributes;
    @Data
    public static class Attributes {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        int id;
        private String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StrapiConstants.pattern)
        private Date createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StrapiConstants.pattern)
        private Date updatedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StrapiConstants.pattern)
        private Date publishedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StrapiConstants.pattern)
        private Date start;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StrapiConstants.pattern)
        private Date end;
        private String tenantId;
        private String memberId;
        private String instructorId;
        private String serviceTypeId;
        private Boolean confirmed;
        private String tenantRole;
        private Boolean cancelled;
    }
}
