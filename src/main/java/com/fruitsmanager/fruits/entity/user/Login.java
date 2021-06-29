package com.fruitsmanager.fruits.entity.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Login {

    /**
     * 用户名
     */
    @NotBlank(message = "存在参数为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$",message = "用户名格式错误")//用户名正则，4到16位（字母，数字，下划线，减号）
    private String username;
    /**
     * 用户密码
     */
    @NotBlank(message = "存在参数为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "用户密码格式错误")//密码强度正则，(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
    private String password;


}
