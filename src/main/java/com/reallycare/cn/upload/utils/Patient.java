package com.reallycare.cn.upload.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: 孙宇豪
 * @Date: 2019/1/18 17:14
 * @Description: TODO 就诊人
 * @Version 1.0
 */
@Entity
@Table(name = "p_patient")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Accessors(chain = true)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //openId
    private String openId;
    //就诊人名字
    private String name;
    //诊疗卡号
    private String cardId;
    //就诊人电话
    private String tel;
    //就诊人身份证、
    private String idcard;
    //就诊人预约时间
    @Temporal(TemporalType.DATE)
    @Column(name = "appointment_time")
    private Date appointmentTime;
    //性别
    private String gender;
    //婚否
    private String marriage;

}
