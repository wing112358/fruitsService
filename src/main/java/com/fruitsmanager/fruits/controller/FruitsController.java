package com.fruitsmanager.fruits.controller;

import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.service.FruitsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Fruits)表控制层
 *
 * @author makejava
 * @since 2021-06-18 15:27:12
 */
@RestController
@RequestMapping("fruits")
public class FruitsController {
    /**
     * 服务对象
     */
    @Resource
    private FruitsService fruitsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Fruits selectOne(Integer id) {
        return this.fruitsService.queryById(id);
    }

}