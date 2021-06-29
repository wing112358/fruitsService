package com.fruitsmanager.fruits.utils;

public enum Resultcode {

    SUCCESS("调用成功", 200),
    REDIRECT("重定向", 301),
    NOTFOUND("请求失败：请求不存在", 404),
    INNERERROR("请求失败：内部服务器错误", 500),
    NOTUSERERROR("请求失败，用户或密码错误", 1001),
    NOTLOGINRERROR("请求失败，请检查登录信息", 1002);

    // 成员变量
    private String massege;
    private int code;


    //枚举类型的构造函数默认为private，因为枚举类型的初始化要在当前枚举类中完成。
    Resultcode (String massege,int code){
        this.massege= massege;
        this.code= code;
    }

    public int getCode(){
        return code;
    }

    public String getmassage(){
        return massege;
    }


}
