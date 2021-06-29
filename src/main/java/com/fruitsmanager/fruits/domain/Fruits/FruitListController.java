package com.fruitsmanager.fruits.domain.Fruits;


import com.fruitsmanager.fruits.domain.BasicController;
import com.fruitsmanager.fruits.entity.common.ListResponse;
import com.fruitsmanager.fruits.entity.dataBase.Fruits;
import com.fruitsmanager.fruits.entity.dataBase.User;
import com.fruitsmanager.fruits.entity.fruit.FruitList;
import com.fruitsmanager.fruits.utils.PageUtil;
import com.fruitsmanager.fruits.utils.Result;
import com.fruitsmanager.fruits.utils.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class FruitListController extends BasicController {

    /**
     * 查询水果列表
     */
    @CrossOrigin
    @RequestMapping(value="/fruit/list",method = RequestMethod.POST)
    public Result fruitsList(HttpServletRequest request, @Validated @RequestBody FruitList fruitList, BindingResult bindingResult){


        Result result=new Result();

        if(bindingResult.hasErrors()){
            String paramerrormassege=bindingResult.getFieldError().getDefaultMessage();
            log.info("这里是获取的异常信息::: "+paramerrormassege);
            result.regularerror(paramerrormassege);
        }
        else{
            String token=request.getHeader("token");
            log.info("获取的token：：："+token);
            Boolean tokenverify=Token.verify(token);
            if(token==null||!tokenverify){
                result.loginerror();
            }
            else{
                try {

                    List<Fruits> fruitslist=fruitsService.queryAll(fruitList);
                    Integer totalcount = fruitslist.size();

                    int start = (fruitList.getIndex()-1) * fruitList.getPagesize();

                    int end= fruitList.getIndex() * fruitList.getPagesize();

                    List<Fruits> afterlist= PageUtil.handlePage(fruitslist,start,end);

                    ListResponse responselist= new ListResponse(totalcount,afterlist);



                    result.ok(responselist);

                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                }

            }
        }

        return result;
    }



}
