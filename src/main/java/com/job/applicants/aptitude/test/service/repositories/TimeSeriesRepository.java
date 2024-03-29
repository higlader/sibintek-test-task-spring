package com.job.applicants.aptitude.test.service.repositories;

import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Tag;
import com.job.applicants.aptitude.test.service.mock.H2Point;
import com.job.applicants.aptitude.test.service.mock.H2Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface TimeSeriesRepository {

    Stream<Tag> tags(List<String> tagNameFilter);

    Stream<Point> points(List<Tag> tagFilter, LocalDateTime start);

    Stream<Point> points(List<Tag> tagFilter, LocalDateTime start, LocalDateTime end);

    Stream<Point> getAllPoint();

    Stream<Point> getAllLastFivePoint();

    Stream<Tag> getAllTag();

    H2Tag getH2TagByName(String tagName);

    Stream<H2Tag> getAllH2Tag();

}
