package com.ycjw.technology.model.project;

import com.ycjw.technology.model.request.project.AddProject;
import com.ycjw.technology.model.user.Mentor;
import com.ycjw.technology.model.user.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("项目ID")
    private int id;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目负责人")
    private String projectLeader;
    @ApiModelProperty("参与导师")
    @ElementCollection
    private Set<String> mentorUsernames = new HashSet<>();
    @ApiModelProperty("项目开始时间")
    private Timestamp startTime;
    @ApiModelProperty("项目截止时间")
    private Timestamp lastTime;
    @ApiModelProperty("项目经费")
    private double funding;
    @ApiModelProperty("项目经费安排")
    @ElementCollection
    private Set<Integer> dealIds = new HashSet<>();
    @ApiModelProperty("项目描述")
    private String projectDetail;
    @ApiModelProperty("项目进度")
    private String projectProgress;
    @ApiModelProperty("项目是否完成")
    private Boolean isFinsh = false;
    @ApiModelProperty("项目成果")
    @OneToOne(targetEntity = Result.class)
    private Result result;

    public Project() {
    }

    public Project(AddProject addProject){
        this.name = addProject.getName();
        this.projectLeader = addProject.getProjectLeader();
        this.mentorUsernames = addProject.getMentorUsernames();
        this.startTime = addProject.getStartTime();
        this.lastTime = addProject.getLastTime();
        this.funding = addProject.getFunding();
        this.projectDetail = addProject.getProjectDetail();
    }

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

    public Set<Integer> getDealIds() {
        return dealIds;
    }

    public void setDealIds(Set<Integer> dealIds) {
        this.dealIds = dealIds;
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
