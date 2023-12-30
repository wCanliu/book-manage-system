package com.gin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  归还模块的实体类
 */
public class Guihuan {

    private Integer id;

    private Integer jieyueid;
    private Integer shujiid;
    private String mingcheng;
    private Integer fenlei;
    private String zuozhe;
    private String yonghu;
    private String addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJieyueid() {
        return jieyueid;
    }

    public void setJieyueid(Integer jieyueid) {
        this.jieyueid = jieyueid == null ? 0 : jieyueid;
    }

    public Integer getShujiid() {
        return shujiid;
    }

    public void setShujiid(Integer shujiid) {
        this.shujiid = shujiid == null ? 0 : shujiid;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng == null ? "" : mingcheng.trim();
    }

    public Integer getFenlei() {
        return fenlei;
    }

    public void setFenlei(Integer fenlei) {
        this.fenlei = fenlei == null ? 0 : fenlei;
    }

    public String getZuozhe() {
        return zuozhe;
    }

    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe == null ? "" : zuozhe.trim();
    }

    public String getYonghu() {
        return yonghu;
    }

    public void setYonghu(String yonghu) {
        this.yonghu = yonghu == null ? "" : yonghu.trim();
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? "" : addtime.trim();
    }
}
