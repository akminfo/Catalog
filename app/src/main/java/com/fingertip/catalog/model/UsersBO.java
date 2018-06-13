package com.fingertip.catalog.model;

/**
 * Created by lenevo on 23-05-2018.
 */

public class UsersBO {
    String id;
    String fname;
    String lname;
    String gender;
    String username;
    String pass;
    String gstin;
    String usegr;
    String email;
    String mob;
    String status;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getGstin() {
        return gstin;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getMob() {
        return mob;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setUsegr(String usegr) {
        this.usegr = usegr;
    }

    public String getUsegr() {
        return usegr;
    }

}
