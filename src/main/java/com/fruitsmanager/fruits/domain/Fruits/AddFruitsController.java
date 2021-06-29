package com.fruitsmanager.fruits.domain.Fruits;


import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.fruit.AddFruit;
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
public class AddFruitsController extends BasicController {

    /**
     * 新增水果记录
     */
    @Transactional
    @CrossOrigin
    @RequestMapping(value="/fruit/add",method = RequestMethod.POST)
    public Result addFruit(HttpServletRequest request, @Validated @RequestBody AddFruit addFruit, BindingResult bindingResult){

        //声明结果
        Result result = new Result();


        //校验token
        String token=request.getHeader("token");
        String username=Token.getValue(token,"username");

        User user=userService.queryByName(username);
        Integer userid=user.getId();

        addFruit.setUsername(username);
        addFruit.setUserid(userid);
        log.info("获取的token：：："+token);
        log.info("addparams:::"+addFruit.toString());
        Boolean tokenverify= Token.verify(token);
        if(token==null||!tokenverify) {
            result.loginerror();
        }else{
            //校验参数
            if(bindingResult.hasErrors()){
                String parammassge = bindingResult.getFieldError().getDefaultMessage();
                result.regularerror(parammassge);
            }
            else{
                //校验记录重复
                Fruits fruits= queryFruitBYName(addFruit.getFruitname());

                if(fruits == null){
                    try{
                        //插入数据
                        log.info("校验查询结果：：：无重复数据");
                        int insertresult= fruitsService.insert(addFruit);
                        log.info("insertflag:::"+insertresult);
                        if(insertresult != 0){
                            //查询数据
                            Fruits insert = queryFruitBYNameAndUser(addFruit.getFruitname(),token);
                            result.ok(insert);
                        }

                    }
                    catch (Exception e){
                        e.printStackTrace();
                        //手动回滚事务
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return result.innererror();
                    }

                }else {
                    log.info("校验查询结果：：："+fruits.toString());
                    result.regularerror("当前数据已存在，请勿重复添加");
                }

            }
        }



        return result;
    }
}
