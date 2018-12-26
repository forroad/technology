package com.ycjw.technology.model.project;

import com.ycjw.technology.model.user.Mentor;
import com.ycjw.technology.model.user.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("项目ID")
    private int id;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目负责人Id")
    private int mentorId;
    @ApiModelProperty("参与导师")
    @Transient
    private List<Mentor> mentors;
    @ApiModelProperty("项目开始时间")
    private Timestamp startTime;
    @ApiModelProperty("项目截止时间")
    private Timestamp lastTime;
    @ApiModelProperty("项目经费")
    private double funding;
    @ApiModelProperty("项目经费安排")
    @Transient
    private List<Deal> deals;
    @ApiModelProperty("项目描述")
    private String projectDetail;
    @ApiModelProperty("项目进度")
    private String projectProgress;
    @ApiModelProperty("项目是否完成")
    private Boolean isFinsh = false;
    @ApiModelProperty("项目成果")
    @Transient
    private Result result;

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

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public double getFunding() {
        return funding;
    }

    public void setFunding(double funding) {
        this.funding = funding;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
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

    public Boolean getFinsh() {
        return isFinsh;
    }

    public void setFinsh(Boolean finsh) {
        isFinsh = finsh;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
