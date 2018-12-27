package com.ycjw.technology.model.request.project;

import java.sql.Timestamp;

public class ModifySoftwareright {
    private int id;
    private String name;
    private String copyrightOwner;
    private Timestamp finishTime;
    private Timestamp publishTime;
    private String AuthorizationWay;
    private String RightRange;
    private String softwareId;

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

    public String getCopyrightOwner() {
        return copyrightOwner;
    }

    public void setCopyrightOwner(String copyrightOwner) {
        this.copyrightOwner = copyrightOwner;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthorizationWay() {
        return AuthorizationWay;
    }

    public void setAuthorizationWay(String authorizationWay) {
        AuthorizationWay = authorizationWay;
    }

    public String getRightRange() {
        return RightRange;
    }

    public void setRightRange(String rightRange) {
        RightRange = rightRange;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }
}
