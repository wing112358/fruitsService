package com.fruitsmanager.fruits.dao;

import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.fruit.AddFruit;
import com.fruitsmanager.fruits.entity.fruit.DelFruits;
import com.fruitsmanager.fruits.entity.fruit.FruitList;
import com.fruitsmanager.fruits.entity.fruit.UpdateFruits;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Fruits)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-18 15:27:12
 */
public interface FruitsDao {

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
     * @param fruitname 主键
     * @return 实例对象
     */
    Fruits queryByName(String fruitname,String username);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Fruits> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Fruits> queryAll(FruitList fruitList);



    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Fruits> queryAllByConditions(@Param("offset") int offset, @Param("limit") int limit, Fruits fruits);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param fruits 实例对象
     * @return 对象列表
     */
    List<Fruits> queryAll(Fruits fruits);

    /**
     * 新增数据
     *
     * @param addFruit 实例对象
     * @return 影响行数
     */
    int insert(AddFruit addFruit);

    /**
     * 修改数据
     *
     * @param updateFruits 实例对象
     * @return 影响行数
     */
    int update(UpdateFruits updateFruits);

    /**
     * 修改状态
     *
     * @param delFruits 实例对象
     * @return 影响行数
     */
    int updateStatus(DelFruits delFruits);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}