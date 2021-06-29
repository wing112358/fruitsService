package com.fruitsmanager.fruits.entity.common;


import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> {

    private Integer totalcount;

    private List<T> list;

    public ListResponse(Integer totalcount, List<T> list){
        this.totalcount=totalcount;
        this.list=list;

    }
}
