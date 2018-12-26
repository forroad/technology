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
    @OneToOne(targetEntity = Mentor.class)
    private Mentor mentor;
    @ApiModelProperty("项目id")
    @OneToOne(targetEntity = Project.class)
    private Project project;
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

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
