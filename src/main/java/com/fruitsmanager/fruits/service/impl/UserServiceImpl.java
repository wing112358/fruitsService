package com.fruitsmanager.fruits.service.impl;

import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.dao.UserDao;
import com.fruitsmanager.fruits.entity.user.Regiester;
import com.fruitsmanager.fruits.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-06-18 15:21:18
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }
    /**
     * 通过NAME查询单条数据
     *
     * @param username
     * @return 实例对象
     */
    @Override
    public User queryByName(String username) {
        return this.userDao.queryByName(username);
    }
    /**
     * 通过NAME和PASSWORD查询单条数据
     *
     * @param username
     * @return 实例对象
     */
    @Override
    public User queryByNameAndPassWord(String username, String password) {
        return this.userDao.queryByNameAndPassWord(username,password);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param regiester 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Regiester regiester) {
        int flag=this.userDao.insert(regiester);
        return flag;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }
}