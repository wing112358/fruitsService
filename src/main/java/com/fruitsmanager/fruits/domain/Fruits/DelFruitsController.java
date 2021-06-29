package com.fruitsmanager.fruits.domain.Fruits;


import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.fruit.DelFruits;
import com.fruitsmanager.fruits.utils.Result;
import com.fruitsmanager.fruits.utils.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class DelFruitsController extends BasicController {


    @Transactional
    @CrossOrigin
    @RequestMapping(value = "/fruit/del",method = RequestMethod.POST)
    public Result delfruit(HttpServletRequest request, @Validated @RequestBody DelFruits delFruits,BindingResult bindingResult){

        //声明结果
        Result result = new Result();


        //校验token
        String token = request.getHeader("token");

        String username=Token.getValue(token,"username");
        User user=userService.queryByName(username);
        Integer userid=user.getId();

        delFruits.setUserid(userid);
        delFruits.setUsername(username);
        log.info("获取的token：：："+token);
        log.info("delparams:::"+delFruits.toString());
        Boolean tokenverify = Token.verify(token);
        if (token == null || !tokenverify) {
            result.loginerror();
        } else {
            //校验参数
            if (bindingResult.hasErrors()) {
                String parammassge = bindingResult.getFieldError().getDefaultMessage();
                result.regularerror(parammassge);
            } else {
                //校验数据是否存在
                Fruits fruits = fruitsService.queryDelById(delFruits.getId());

                if (fruits == null) {
                    log.info("执行前查询无结果");
                    try {
                        //执行更新操作
                        delFruits.setStatus(3);
                        int updatestatusresult = fruitsService.updateStatus(delFruits);
                        log.info("delflag:::"+updatestatusresult);
                        if (updatestatusresult != 0) {
                            //查询数据
                            Fruits update = fruitsService.queryDelById(delFruits.getId());
                            log.info("执行后查询结果：：："+update.toString());
                            result.ok(update);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        //手动回滚事务
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return result.innererror();
                    }

                }
                else {
                    log.info("执行前存在已删除：：："+fruits.toString());
                    result.regularerror("请勿重复操作");
                }
            }
        }


        return result;
    }



}
