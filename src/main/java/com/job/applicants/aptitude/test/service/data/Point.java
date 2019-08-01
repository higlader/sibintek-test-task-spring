package com.job.applicants.aptitude.test.service.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Point {

    private Tag tag;
    private LocalDateTime ts;
    private Long value;
    private Quality quality;

    public Point(Tag tag, LocalDateTime ts, Long value, Quality quality) {
        this.tag = tag;
        this.ts = ts;
        this.value = value;
        this.quality = quality;
    }

    public Point(String tagName, String tagDescription, LocalDateTime ts, Long value, Quality quality) {
        this.tag = new Tag(tagName, tagDescription);
        this.ts = ts;
        this.value = value;
        this.quality = quality;
    }
}
