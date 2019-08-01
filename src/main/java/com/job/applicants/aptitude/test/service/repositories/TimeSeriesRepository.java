package com.job.applicants.aptitude.test.service.repositories;

import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface TimeSeriesRepository {

    Stream<Tag> tags(List<String> tagNameFilter);

    Stream<Point> points(List<Tag> tagFilter, LocalDateTime start);

    Stream<Point> points(List<Tag> tagFilter, LocalDateTime start, LocalDateTime end);


}
