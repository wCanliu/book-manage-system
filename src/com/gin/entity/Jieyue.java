package com.gin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  借阅模块的实体类
 */
public class Jieyue {

    private Integer id;

    private Integer shujiid;
    private String mingcheng;
    private Integer fenlei;
    private String zuozhe;
    private String yonghu;
    private String kaishi;
    private String jieshu;
    private Integer shizhang;
    private String zhuangtai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getKaishi() {
        return kaishi;
    }

    public void setKaishi(String kaishi) {
        this.kaishi = kaishi == null ? "" : kaishi.trim();
    }

    public String getJieshu() {
        return jieshu;
    }

    public void setJieshu(String jieshu) {
        this.jieshu = jieshu == null ? "" : jieshu.trim();
    }

    public Integer getShizhang() {
        return shizhang;
    }

    public void setShizhang(Integer shizhang) {
        this.shizhang = shizhang == null ? 0 : shizhang;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai == null ? "" : zhuangtai.trim();
    }
}
