package com.shawn.recruit.entity;

import com.shawn.recruit.common.StateEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_recruit")
public class Recruit implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "salary")
    private String salary;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "education")
    private String education;

    @Column(name = "type")
    private String type;

    @Column(name = "address")
    private String address;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(name = "state")
    private StateEnum state;

    @Column(name = "url")
    private String url;

    @Column(name = "label")
    private String label;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "requirement_description")
    private String requirementDescription;

}

