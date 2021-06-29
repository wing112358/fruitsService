package com.fruitsmanager.fruits.domain.User;


import com.fruitsmanager.fruits.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class LogoutController {

    /**
     * 登出方法
     */

    @RequestMapping(value = "/user/logout",method = RequestMethod.POST)
    @CrossOrigin
    public Result logout(HttpServletRequest request){

        Result result = new Result();

        //校验token
        String token=request.getHeader("token");
        log.info("获取的token：：："+token);
        if(token==null){
            result.regularerror("缺少登录信息");
        }else{
            result.ok(null);
        }

        return result;

    }
}
