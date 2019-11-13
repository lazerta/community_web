package com.shawn.community.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_label")
public class Label implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "state")
    private String state;

    @Column(name = "count")
    private Long count;

    @Column(name = "recommend")
    private String recommend;

    @Column(name = "fans_count")
    private Long fansCount;

}
