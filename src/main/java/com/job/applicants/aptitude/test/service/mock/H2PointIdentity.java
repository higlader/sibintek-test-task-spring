package com.job.applicants.aptitude.test.service.mock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class H2PointIdentity implements Serializable {

    private H2Tag tag;
    private LocalDateTime ts;

    public H2PointIdentity(H2Tag tag, LocalDateTime ts) {
        this.tag = tag;
        this.ts = ts;
    }
}
