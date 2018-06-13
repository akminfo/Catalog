package com.fingertip.catalog.model;

/**
 * Created by lenevo on 28-05-2018.
 */

public class OrderViewBO {
    String proid;
    String orderid;
    String name;
    String sku;
    String quant;
    String unit;
    String total;
    String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProid() {
        return proid;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getQuant() {
        return quant;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
