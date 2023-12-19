package com.weeks2.strapi.model;

import lombok.Data;

import java.util.Date;
@Data
public class Lesson {
    Integer id;
    private String title;
    private Date createdAt;
    private Date updatedAt;
    private Date publishedAt;
    private Date start;
    private Date end;
    private String tenantId;
    private String memberId;
    private String instructorId;
    private String serviceTypeId;
    private boolean confirmed;
    private String tenantRole;
    private boolean cancelled;
}
