package com.ycjw.technology.model.request.project;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddProject{
    private String name;
    private String projectLeader;
    private Set<String> mentorUsernames = new HashSet<>();
    private Timestamp startTime;
    private Timestamp lastTime;
    private double funding;
    private String projectDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Set<String> getMentorUsernames() {
        return mentorUsernames;
    }

    public void setMentorUsernames(Set<String> mentorUsernames) {
        this.mentorUsernames = mentorUsernames;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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