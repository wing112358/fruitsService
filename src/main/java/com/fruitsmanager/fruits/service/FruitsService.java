package com.fruitsmanager.fruits.service;

import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.fruit.AddFruit;
import com.fruitsmanager.fruits.entity.fruit.DelFruits;
import com.fruitsmanager.fruits.entity.fruit.FruitList;
import com.fruitsmanager.fruits.entity.fruit.UpdateFruits;

import java.util.List;

/**
 * (Fruits)表服务接口
 *
 * @author makejava
 * @since 2021-06-18 15:27:12
 */
public interface FruitsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Fruits queryById(Integer id);

    /**
     * 通过ID查询单条已删除数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Fruits queryDelById(Integer id);

    /**
     * 通过NAME查询单条数据
     *
     * @param name 主键
     * @return 实例对象
     */
    Fruits queryByName(String name,String username);




    /**
     * 查询多条数据
     *
     *
     * @return 对象列表
     */
    List<Fruits> queryAll(FruitList fruitList);


    /**
     * 查询多条数据
     *
     *
     * @return 对象列表
     */

    List<Fruits> queryAllByConditions(int offset, int limit, Fruits fruits);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Fruits> queryAllByLimit(int offset, int limit);



    /**
     * 新增数据
     *
     * @param addFruit 实例对象
     * @return 实例对象
     */
    int insert(AddFruit addFruit);

    /**
     * 修改数据
     *
     * @param updateFruits 实例对象
     * @return 实例对象
     */
    int update(UpdateFruits updateFruits);

    /**
     * 修改状态
     *
     * @param delFruits 实例对象
     * @return 实例对象
     */
    int updateStatus(DelFruits delFruits);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}