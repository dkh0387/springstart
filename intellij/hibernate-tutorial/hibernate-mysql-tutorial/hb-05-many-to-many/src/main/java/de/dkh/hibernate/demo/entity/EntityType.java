package de.dkh.hibernate.demo.entity;

import lombok.Getter;

public enum EntityType {
    REVIEW("review"),
    COURSE_STUDENT("course_student"),
    COURSE("course"),
    INSTRUCTOR("instructor"),
    INSTRUCTOR_DETAIL("instructor_detail"),
    STUDENT("student");

    @Getter
    private String tableName;

    EntityType(String tableName) {
        this.tableName = tableName;
    }
}
