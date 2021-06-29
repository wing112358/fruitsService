package com.fruitsmanager.fruits.entity.dataBase;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * (Fruits)实体类
 *
 * @author makejava
 * @since 2021-06-18 15:27:12
 */

@Data
public class Fruits implements Serializable {
    private static final long serialVersionUID = 918035542503503532L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 水果名称
    */
    private String fruitname;
    /**
    * 库存
    */
    private Long stock;
    /**
    * 水果状态：0 未上架，1 已上架 2 已下架 3 已删除
    */
    private Integer status;
    /**
    * 备注
    */
    private String desc;
    /**
     * 用户id
     */
    private Integer userid;
    /**
     * 创建用户名称
     */
    private String username;
    /**
     * 创建时间
     */
    private Timestamp create_time;

    /**
     * 更新时间
     */
    private Timestamp update_time;



}