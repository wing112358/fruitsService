package com.fruitsmanager.fruits.domain.User;

import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.user.Regiester;
import com.fruitsmanager.fruits.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@RestController
public class RegisterController extends BasicController {


    /**
     * 注册方法
     */
    @CrossOrigin
    @RequestMapping(value = "/user/regiester",method = RequestMethod.POST)
    public Result regiester(@Validated @RequestBody Regiester regiester, BindingResult bindingResult){

        //声明结果参数，打印请求参数
        Result result=new Result();
        //处理时间
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());

        log.info("格式化输出：" + time);

        regiester.setCreate_time(time);

        log.info("regiesterparams:::"+regiester.toString());

        try{
            //校验参数
            if(bindingResult.hasErrors()){
                String parammassge = bindingResult.getFieldError().getDefaultMessage();
                result.regularerror(parammassge);
            }
            else{
                if(regiester.getPassword().equals(regiester.getCheckpassword())){

                    //校验是否数据已存在
                    Boolean checkquery=checkUserBYName(regiester.getUsername());
                    if(checkquery){
                        //发起插入操作
                        int inseruser=userService.insert(regiester);
                        log.info("插入标志：：："+inseruser);
                        if(inseruser!=0){
                            User userquery=userService.queryByName(regiester.getUsername());
                            log.info("插入后校验结果：：："+userquery.toString());
                            result.ok(userquery);
                        }
                        else{
                            result.regularerror("插入失败");
                        }
                    }else{
                        result.regularerror("用户名重复");
                    }
                }else{
                    result.regularerror("密码不一致");
                }

            }



        }catch (RuntimeException e){
            e.printStackTrace();
        }


        return result;
    }
}
