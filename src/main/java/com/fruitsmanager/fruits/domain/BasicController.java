package com.fruitsmanager.fruits.domain;

import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.service.FruitsService;
import com.fruitsmanager.fruits.service.UserService;
import com.fruitsmanager.fruits.utils.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class BasicController
{

    /**
     * 服务对象--------------------------
     */
    @Resource
    public UserService userService;


    @Resource
    public FruitsService fruitsService;

    /**
     * 公共方法--------------------------
     */

    /**
     * 根据name查询记录
     * @param name
     * @return
     */


    public Fruits queryFruitBYName(String name){

        Fruits queryresult=fruitsService.queryByName(name,"");

        return queryresult;


    }

    public Fruits queryFruitBYNameAndUser(String name,String token){

        String username= Token.getValue(token,"username");
        log.info("token中解析出来的username：：：："+username);
        Fruits queryresult=fruitsService.queryByName(name,username);

        return queryresult;


    }


    /**
     * 根据用户名判断用户是否存在（不包含删除），存在返回false，不存在返回true
     * @param name
     * @return
     */
    public Boolean checkUserBYName(String name){

        User queryresult=userService.queryByName(name);

        boolean result=queryresult==null ? true : false;

        return result;


    }
}
