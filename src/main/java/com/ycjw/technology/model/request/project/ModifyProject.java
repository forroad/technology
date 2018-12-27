package com.ycjw.technology.model.request.project;

import java.sql.Timestamp;

public class ModifyProject {
    private int id;
    private String name;
    private Timestamp lastTime;
    private double funding;
    private String projectDetail;

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

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public double getFunding() {
        return funding;
    }

    public void setFunding(double funding) {
        this.funding = funding;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }
}
