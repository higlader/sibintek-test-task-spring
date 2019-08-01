package com.job.applicants.aptitude.test.service.data;


import lombok.Data;

@Data
public class Tag {

    private String name;
    private String description;

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
