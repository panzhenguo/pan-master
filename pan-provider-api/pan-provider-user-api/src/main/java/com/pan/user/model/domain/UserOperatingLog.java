package com.pan.user.model.domain;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "aw_user_operating_log")
public class UserOperatingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作内容
     */
    @Column(name = "log_info")
    private String logInfo;

    /**
     * 耗时
     */
    @Column(name = "use_time")
    private Long useTime;

    /**
     * 操作方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 方法参数
     */
    @Column(name = "method_params")
    private String methodParams;

    /**
     * 用户IP
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * 操作时间
     */
    @Column(name = "crate_time")
    private Date crateTime;
}