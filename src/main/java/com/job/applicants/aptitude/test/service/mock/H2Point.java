package com.job.applicants.aptitude.test.service.mock;


import com.job.applicants.aptitude.test.service.data.Quality;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "points")
public class H2Point implements Serializable {

    @EmbeddedId
    private H2PointIdentity h2PointIdentity;
    private Long value;
    private Quality quality;

    public H2Point(H2PointIdentity h2PointIdentity, Long value, Quality quality) {
        this.h2PointIdentity = h2PointIdentity;
        this.value = value;
        this.quality = quality;
    }
}
