package com.fruitsmanager.fruits.service.impl;

import com.fruitsmanager.fruits.dao.FruitsDao;
import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.fruit.AddFruit;
import com.fruitsmanager.fruits.entity.fruit.DelFruits;
import com.fruitsmanager.fruits.entity.fruit.FruitList;
import com.fruitsmanager.fruits.entity.fruit.UpdateFruits;
import com.fruitsmanager.fruits.service.FruitsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Fruits)表服务实现类
 *
 * @author makejava
 * @since 2021-06-18 15:27:12
 */
@Service("fruitsService")
public class FruitsServiceImpl implements FruitsService {
    @Resource
    private FruitsDao fruitsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Fruits queryById(Integer id) {
        return this.fruitsDao.queryById(id);
    }

    /**
     * 通过ID查询单条已删除数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Fruits queryDelById(Integer id) {
        return this.fruitsDao.queryDelById(id);
    }


    /**
     * 通过NAME查询单条数据
     *
     * @param
     * @return 实例对象
     */
    @Override
    public Fruits queryByName(String fruitname,String username) {
        return this.fruitsDao.queryByName(fruitname,username);
    }


    /**
     * 根据条件查询多条数据
     *
     *
     * @return 对象列表
     */
    @Override
    public List<Fruits> queryAll(FruitList fruitList) {
        return this.fruitsDao.queryAll(fruitList);
    }



    /**
     * 根据条件查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Fruits> queryAllByConditions(int offset, int limit, Fruits fruits) {
        return this.fruitsDao.queryAllByConditions(offset, limit, fruits);
    }
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Fruits> queryAllByLimit(int offset, int limit) {
        return this.fruitsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param addFruit 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(AddFruit addFruit) {
        int insertflag=this.fruitsDao.insert(addFruit);
        return insertflag;
    }

    /**
     * 修改数据
     *
     * @param updateFruits 实例对象
     * @return 实例对象
     */
    @Override
    public int update(UpdateFruits updateFruits){
        return this.fruitsDao.update(updateFruits);
    }

    /**
     * 修改状态
     *
     * @param delFruits 实例对象
     * @return 实例对象
     */
    @Override
    public int updateStatus(DelFruits delFruits) {
        return this.fruitsDao.updateStatus(delFruits);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.fruitsDao.deleteById(id) > 0;
    }
}