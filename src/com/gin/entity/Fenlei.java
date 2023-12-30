package com.gin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  分类模块的实体类
 */
public class Fenlei {

    private Integer id;

    private String fenleiming;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFenleiming() {
        return fenleiming;
    }

    public void setFenleiming(String fenleiming) {
        this.fenleiming = fenleiming == null ? "" : fenleiming.trim();
    }
}
