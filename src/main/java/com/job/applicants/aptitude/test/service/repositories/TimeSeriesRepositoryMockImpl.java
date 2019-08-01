package com.job.applicants.aptitude.test.service.repositories;

import com.job.applicants.aptitude.test.service.data.Point;
import com.job.applicants.aptitude.test.service.data.Tag;
import com.job.applicants.aptitude.test.service.mock.H2MockPointsRepository;
import com.job.applicants.aptitude.test.service.mock.H2MockTagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TimeSeriesRepositoryMockImpl implements TimeSeriesRepository {

    private H2MockPointsRepository h2MockPointsRepository;
    private H2MockTagsRepository h2MockTagsRepository;

    public TimeSeriesRepositoryMockImpl(H2MockPointsRepository mockPointsRepository, H2MockTagsRepository mockTagsRepository) {
        this.h2MockPointsRepository = mockPointsRepository;
        this.h2MockTagsRepository = mockTagsRepository;
    }

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
}
