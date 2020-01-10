package com.shawn.community.meetup.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_meetup")
public class Meetup implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;

    @Column(name = "detail")
    private String detail;

    @Column(name = "sponsor")
    private String sponsor;

    @Column(name = "image")
    private String image;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "address")
    private String address;

    @Column(name = "enroll_deadline")
    private Date enrollDeadline;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

}
