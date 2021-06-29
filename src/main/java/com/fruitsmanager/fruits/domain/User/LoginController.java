package com.fruitsmanager.fruits.domain.User;

import com.alibaba.fastjson.JSONObject;
import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.user.Login;
import com.fruitsmanager.fruits.utils.Result;
import com.fruitsmanager.fruits.utils.Token;
import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Log
@RestController
public class LoginController extends BasicController {

    @CrossOrigin
    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public Result Login(HttpServletRequest request, @RequestBody  @Validated Login login, BindingResult bindingResult){


        Result loginresult=new Result();

        log.info("loginparams:::"+login.toString());

        if(bindingResult.hasErrors()){
            String paramerrormassege=bindingResult.getFieldError().getDefaultMessage();
            log.info("这里是获取的异常信息：：： "+paramerrormassege);

            loginresult.regularerror(paramerrormassege);
        }
        else{
            try{
                //根据用户姓名以及密码查询用户信息
                User passwordresult=userService.queryByName(login.getUsername());
                String pwd=passwordresult.getPassword();
                log.info("查询用户信息结果：：："+passwordresult.toString());

                //返回token
                JSONObject responce=new JSONObject();

                //判断是否存在对应记录
                if(passwordresult!=null& pwd.equals(login.getPassword())){
                    Token tokenclass=new Token();
                    String token=tokenclass.token(login.getUsername(), login.getPassword());

                    responce.put("token",token);
                    loginresult.ok(responce);

                }
                else if(!pwd.equals(login.getPassword())){
                    loginresult.regularerror("用户名或密码错误");
                }else{
                    loginresult.usererror();
                }
                 }catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                }
        }



        return loginresult;
    }


}
