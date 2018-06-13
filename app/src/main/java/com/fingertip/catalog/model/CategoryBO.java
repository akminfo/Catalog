package com.fingertip.catalog.model;

/**
 * Created by lenevo on 08-05-2018.
 */

public class CategoryBO {
    String catname;
    String status;
    String sort;
    String id;
    String top;
    String desc;
    String meta_title;
    String meta_decs;
    String meta_key;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setMeta_decs(String meta_decs) {
        this.meta_decs = meta_decs;
    }

    public String getMeta_decs() {
        return meta_decs;
    }

    public void setMeta_key(String meta_key) {
        this.meta_key = meta_key;
    }

    public String getMeta_key() {
        return meta_key;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTop() {
        return top;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatname() {
        return catname;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
