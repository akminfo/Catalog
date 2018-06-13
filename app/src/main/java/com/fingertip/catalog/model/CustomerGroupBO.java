package com.fingertip.catalog.model;

/**
 * Created by lenevo on 21-05-2018.
 */

public class CustomerGroupBO {
    String id;
    String name;
    String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
