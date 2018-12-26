package com.ycjw.technology.model.project;

import com.ycjw.technology.model.user.Mentor;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;

@Entity
@Table(name = "t_deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("专利ID")
    private int id;
    @ApiModelProperty("安排导师ID")
    private int mentorId;
    @ApiModelProperty("项目id")
    private int projectId;
    @ApiModelProperty("分配的经费")
    private double funding;
    @ApiModelProperty("任务描述")
    private String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public double getFunding() {
        return funding;
    }

    public void setFunding(double funding) {
        this.funding = funding;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
}
