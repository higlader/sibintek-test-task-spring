package com.job.applicants.aptitude.test.service.mock;

import com.job.applicants.aptitude.test.service.data.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2MockTagsRepository extends CrudRepository<H2Tag, String> {
    H2Tag findH2TagByName(String name);
}
