package com.ycjw.technology.model.request.project;

import java.sql.Timestamp;

public class AddPatent {
    private String name;
    private String creater;
    private String patentId;
    private Timestamp applyTime;
    private String patentee;
    private Timestamp authorizationTime;
    private int projectId;

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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
