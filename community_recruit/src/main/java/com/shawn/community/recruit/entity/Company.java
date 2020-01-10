package com.shawn.community.recruit.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_company")
public class Company implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;

    @Column(name = "address")
    private String address;

    @Column(name = "labels")
    private String labels;

    @Column(name = "coordinate")
    private String coordinate;

    @Column(name = "is_popular")
    private Boolean isPopular;

    @Column(name = "logo")
    private String logo;

    @Column(name = "job_count")
    private Integer jobCount;

    @Column(name = "url")
    private String url;
}
