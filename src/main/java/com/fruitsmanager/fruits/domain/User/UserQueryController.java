package com.fruitsmanager.fruits.domain.User;

import com.alibaba.fastjson.JSONObject;
import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.user.UserQuery;
import com.fruitsmanager.fruits.utils.Result;
import com.fruitsmanager.fruits.utils.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class UserQueryController extends BasicController {

    /**
     * 通过主键查询单条数据
     *
     * @param userQuery
     * @return 单条数据
     */
    @CrossOrigin
    @RequestMapping(value="/queryUserByName",method = RequestMethod.POST)
    public Result queryUserByName(HttpServletRequest request, @RequestBody @Validated UserQuery userQuery, BindingResult bindingResult) {
        JSONObject response=new JSONObject();
        Result result=new Result();

        log.info("queryUserByNameparams:::"+userQuery.toString());

        if(bindingResult.hasErrors()){
            String paramerrormassege=bindingResult.getFieldError().getDefaultMessage();
            log.info("这里是获取的异常信息：：： "+paramerrormassege);

            result.regularerror(paramerrormassege);
        }
        else{
            //校验token
            String token=request.getHeader("token");
            log.info("获取的token：：："+token);
            Boolean tokenverify= Token.verify(token);
            if(token==null||!tokenverify) {
                result.loginerror();}
            else{
                User responce=userService.queryByName(userQuery.getUsername());
                log.info("登录查询用户信息结果：：："+responce.toString());
                if(responce!=null){
                    response.put("User",responce);
                    result.ok(response);
                }
                else {
                    result.usererror();
                }
            }


        }



        return result;

    }

}
