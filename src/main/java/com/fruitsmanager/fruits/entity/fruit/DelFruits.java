package com.fruitsmanager.fruits.entity.fruit;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class DelFruits {

    /**
     * 主键
     */
    @NotNull(message = "请选定需要操作的记录")
    private Integer id;

    /**
     * 水果状态：0 未上架，1 已上架 2 已下架 3 已删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Timestamp update_time;

    /**
     * 用户id
     */
    private Integer userid;
    /**
     * 创建用户名称
     */
    private String username;
}
