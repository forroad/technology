package com.ycjw.technology.model.request.project;

import java.sql.Timestamp;

public class ModifyPatent {
    private int id;
    private String name;
    private String creater;
    private String patentId;
    private Timestamp applyTime;
    private String patentee;
    private Timestamp authorizationTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public String getPatentee() {
        return patentee;
    }

    public void setPatentee(String patentee) {
        this.patentee = patentee;
    }

    public Timestamp getAuthorizationTime() {
        return authorizationTime;
    }

    public void setAuthorizationTime(Timestamp authorizationTime) {
        this.authorizationTime = authorizationTime;
    }
}
