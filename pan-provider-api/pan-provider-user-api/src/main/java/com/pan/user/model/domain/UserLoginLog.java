package com.pan.user.model.domain;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "aw_user_login_log")
public class UserLoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login_user_id")
    private Integer loginUserId;

    @Column(name = "login_date")
    private Date loginDate;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "login_device")
    private String loginDevice;

}