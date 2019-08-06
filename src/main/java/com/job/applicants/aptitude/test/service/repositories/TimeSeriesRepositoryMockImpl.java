package com.job.applicants.aptitude.test.service.repositories;

import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Quality;
import com.job.applicants.aptitude.test.service.data.Tag;
import com.job.applicants.aptitude.test.service.mock.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TimeSeriesRepositoryMockImpl implements TimeSeriesRepository {

    private final H2MockPointsRepository h2MockPointsRepository;
    private final H2MockTagsRepository h2MockTagsRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Stream<Tag> tags(List<String> tagNameFilter) {
        return StreamSupport.stream(h2MockTagsRepository.findAll().spliterator(), false).map(a-> new Tag(a.getName(), a.getDescription()));
    }

    @Override
    public Stream<Point> points(List<Tag> tagFilter, LocalDateTime start) {
        List<String> tagNames = tagFilter.stream().map(Tag::getName).collect(Collectors.toList());
        return h2MockPointsRepository.findByH2PointIdentityTagInAndH2PointIdentityTsAfter(tagNames, start)
                .stream()
                .map(a-> new Point(a.getH2PointIdentity().getTag().getName(), a.getH2PointIdentity().getTag().getDescription(), a.getH2PointIdentity().getTs(), a.getValue(), a.getQuality()));
    }

    @Override
    public Stream<Point> points(List<Tag> tagFilter, LocalDateTime start, LocalDateTime end) {
        List<String> tagNames = tagFilter.stream().map(Tag::getName).collect(Collectors.toList());
        return h2MockPointsRepository.findByH2PointIdentityTagInAndH2PointIdentityTsAfterAndH2PointIdentityTsBefore(tagNames, start, end)
                .stream()
                .map(a-> new Point(a.getH2PointIdentity().getTag().getName(), a.getH2PointIdentity().getTag().getDescription(), a.getH2PointIdentity().getTs(), a.getValue(), a.getQuality()));
    }

    @Override
    public Stream<Tag> getAllTag() {
        return StreamSupport.stream(h2MockTagsRepository.findAll().spliterator(), false).map(a-> new Tag(a.getName(), a.getDescription()));
    }

    @Override
    public H2Tag getH2TagByName(String tagName) {
        return h2MockTagsRepository.findH2TagByName(tagName);
    }

    @Override
    public Stream<H2Tag> getAllH2Tag() {
        return StreamSupport.stream(h2MockTagsRepository.findAll().spliterator(), false);
    }

    @Override
    public Stream<Point> getAllPoint() {
        String getAllFromPoints = "SELECT * FROM points";
        List<H2Point> h2PointList =  jdbcTemplate.queryForObject(getAllFromPoints, new RowMapper<List<H2Point>>() {
            @Nullable
            @Override
            public List<H2Point> mapRow(ResultSet resultSet, int i) throws SQLException {
                List<H2Point> pointList = new ArrayList<>();
                while (resultSet.next()){
                    H2Tag h2Tag = getH2TagByName(resultSet.getString("tag"));
                    LocalDateTime ts = resultSet.getTimestamp("ts").toLocalDateTime();
                    H2PointIdentity h2PointIdentity = new H2PointIdentity(h2Tag,ts);
                    Long value = resultSet.getLong("value");
                    Quality quality = Quality.getQualityFromIndex(Integer.valueOf(resultSet.getString("quality")));
                    H2Point h2Point = new H2Point(h2PointIdentity,value,quality);
                    pointList.add(h2Point);
                }
                return pointList;
            }
        });
        List<Point> pointList = new ArrayList<>();
        for (H2Point h2Point:h2PointList) {
            Tag tag = new Tag(h2Point.getH2PointIdentity().getTag().getName(),h2Point.getH2PointIdentity().getTag().getDescription());
            Point point = new Point(tag,h2Point.getH2PointIdentity().getTs(),h2Point.getValue(),h2Point.getQuality());
            pointList.add(point);
        }

        return pointList.stream();
    }

}
