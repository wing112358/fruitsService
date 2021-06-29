package com.fruitsmanager.fruits.domain.Fruits;


import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.fruit.UpdateFruits;
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
public class UpdateFruitsController extends BasicController {

    /**
     * 更新水果记录
     *
     * @param request
     * @param updateFruits
     * @return
     */

    @Transactional
    @CrossOrigin
    @RequestMapping(value = "/fruit/update", method = RequestMethod.POST)
    public Result delfruits(HttpServletRequest request, @Validated @RequestBody UpdateFruits updateFruits, BindingResult bindingResult) {
        //声明结果
        Result result = new Result();


        //校验token
        String token = request.getHeader("token");
        String username=Token.getValue(token,"username");
        User user=userService.queryByName(username);
        Integer userid=user.getId();

        updateFruits.setUserid(userid);
        updateFruits.setUsername(username);
        log.info("获取的token：：："+token);
        log.info("updateparams:::"+updateFruits.toString());
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
                Fruits fruits = fruitsService.queryById(updateFruits.getId());

                if (fruits != null) {
                    try {
                        //执行更新操作
                        log.info("校验查询到的数据：：："+fruits.toString());
                        //校验重复
                        if(updateFruits.getFruitname()!=null){
                            Fruits getId=queryFruitBYName(updateFruits.getFruitname());
                            if(getId.getId()!=updateFruits.getId()){
                                result.regularerror("名称重复");
                            }else{
                                int updateresult = fruitsService.update(updateFruits);
                                log.info("updatestatusflag:::"+updateresult);
                                if (updateresult != 0) {
                                    //查询数据
                                    Fruits update = fruitsService.queryById(updateFruits.getId());
                                    log.info("执行后查询结果：：："+update.toString());
                                    log.info("删除后结果："+update.toString());
                                    result.ok(update);
                                }
                                else{
                                    result.regularerror("更新失败");
                                }
                            }
                        }else{
                            int updateresult1 = fruitsService.update(updateFruits);
                            log.info("updatestatusflag:::"+updateresult1);
                            if (updateresult1 != 0) {
                                //查询数据
                                Fruits update1 = fruitsService.queryById(updateFruits.getId());
                                log.info("执行后查询结果：：："+update1.toString());
                                log.info("删除后结果："+update1.toString());
                                result.ok(update1);
                            }
                            else{
                                result.regularerror("更新失败");
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        //手动回滚事务
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return result.innererror();
                    }

                }else{
                    log.info("检查结果：：：出现异常");
                    result.regularerror("不存在当前数据");
                }

            }
        }


        return result;


    }
}
