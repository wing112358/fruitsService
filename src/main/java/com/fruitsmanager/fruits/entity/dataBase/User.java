package com.fruitsmanager.fruits.entity.dataBase;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-06-18 15:21:18
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -12268979481270074L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 用户名
    */
    @Pattern(regexp = "/^[a-zA-Z0-9_-]{4,16}$/",message = "用户名格式错误")//用户名正则，4到16位（字母，数字，下划线，减号）
    private String username;
    /**
    * 用户密码
    */
    @Pattern(regexp = "/^.*(?=.{8,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/",message = "用户名格式错误")//密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
    private String password;
    /**
    * 用户备注
    */
    private String desc;
    /**
     * 创建时间
     */
    private Timestamp create_time;



}