package com.job.applicants.aptitude.test.service.mock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface H2MockPointsRepository extends CrudRepository<H2Point, H2Tag> {
    List<H2Point> findByH2PointIdentityTagInAndH2PointIdentityTsAfter(List<String> ids, LocalDateTime after);
    List<H2Point> findByH2PointIdentityTagIn(List<String> ids);
    List<H2Point> findByH2PointIdentityTagInAndH2PointIdentityTsAfterAndH2PointIdentityTsBefore(List<String> ids, LocalDateTime after, LocalDateTime before);
}
