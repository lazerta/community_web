package com.shawn.community.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "password")
    private String password;

    @Column(name = "alias")
    private String alias;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "email")
    private String email;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "login_duration")
    private Long loginDuration;

    @Column(name = "interest")
    private String interest;
    @Column(name = "personality")
    private String personality;

    @Column(name = "fan_count")
    private Integer fanCount;

    @Column(name = "follow_count")
    private Integer followCount;

}
