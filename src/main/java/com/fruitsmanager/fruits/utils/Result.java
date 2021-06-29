package com.fruitsmanager.fruits.utils;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;


    public Result ok(T t){
        Resultcode success=Resultcode.SUCCESS;
        this.setCode(success.getCode());
        this.setMessage(success.getmassage());
        this.setData(t);
        return this;
    }

    public Result redirect(){
        Resultcode redirect=Resultcode.REDIRECT;
        this.setCode(redirect.getCode());
        this.setMessage(redirect.getmassage());
        return this;
    }

    public Result notfound(){
        Resultcode notfound=Resultcode.NOTFOUND;
        this.setCode(notfound.getCode());
        this.setMessage(notfound.getmassage());
        return this;
    }

    public Result innererror(){
        Resultcode innererror=Resultcode.INNERERROR;
        this.setCode(innererror.getCode());
        this.setMessage(innererror.getmassage());
        return this;
    }

    public Result usererror(){
        Resultcode usererror=Resultcode.NOTUSERERROR;
        this.setCode(usererror.getCode());
        this.setMessage(usererror.getmassage());
        return this;
    }

    public Result loginerror(){
        Resultcode loginerror=Resultcode.NOTLOGINRERROR;
        this.setCode(loginerror.getCode());
        this.setMessage(loginerror.getmassage());
        return this;
    }

    public Result regularerror(String massage){
        this.setCode(1003);
        this.setMessage(massage);
        return this;
    }


    public Result() {
    }

//    public Result(Integer code, String message) {
//        this.code = code;
//        this.setMessage(message);
//    }
//
//    public Result(Integer code, String message, JSONObject data) {
//        this.code = code;
//        this.data = data;
//        this.setMessage(message);
//    }
}

