package com.fingertip.catalog.model;

/**
 * Created by lenevo on 19-05-2018.
 */

public class CouponBO {
    String name;
    String code;
    String dis;
    String datestart;
    String dateend;
    String status;
    String id;
    String type;
    String cusgroup;
    String tot;

    String user_percoupon;
    String user_percust;
    String group_id;



    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setUser_percoupon(String user_percoupon) {
        this.user_percoupon = user_percoupon;
    }

    public String getUser_percoupon() {
        return user_percoupon;
    }

    public void setUser_percust(String user_percust) {
        this.user_percust = user_percust;
    }

    public String getUser_percust() {
        return user_percust;
    }

    public void setTot(String tot) {
        this.tot = tot;
    }

    public String getTot() {
        return tot;
    }

    public void setCusgroup(String cusgroup) {
        this.cusgroup = cusgroup;
    }

    public String getCusgroup() {
        return cusgroup;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    public String getDatestart() {
        return datestart;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getDis() {
        return dis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
