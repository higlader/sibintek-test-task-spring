package com.job.applicants.aptitude.test.service.mock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class H2Tag implements Serializable {
    @Id
    private String name;
    private String description;

    public H2Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
