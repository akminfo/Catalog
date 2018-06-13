package com.fingertip.catalog.model;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UserGroupBO {
    String username;
    String id;
    String access;
    String modify;

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAccess() {
        return access;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public String getModify() {
        return modify;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
