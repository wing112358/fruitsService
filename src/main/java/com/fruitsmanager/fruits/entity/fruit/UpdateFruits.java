package com.fruitsmanager.fruits.entity.fruit;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
public class UpdateFruits {

    /**
     * 主键
     */
    @NotNull(message = "请选定需要操作的记录")
    private Integer id;
    /**
     * 水果名称
     */
    @Pattern(regexp ="^[\\u4e00-\\u9fa5]{0,}$" ,message = "水果名称格式不正确")
    private String fruitname;
    /**
     * 库存
     */
    private Long stock;
    /**
     * 水果状态：0 未上架，1 已上架 2 已下架 3 已删除
     */
    @Range(min=0,max=3,message = "状态不正确")
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
