package com.ycjw.technology.model.project;

import com.ycjw.technology.model.user.Mentor;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_patent")
public class Patent
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("专利ID")
    private int id;
    @ApiModelProperty("专利名称")
    private String name;
    @ApiModelProperty("发明人")
    private String creater;
    @ApiModelProperty("专利号")
    private String patentId;
    @ApiModelProperty("专利申请日")
    private Timestamp applyTime;
    @ApiModelProperty("专利权人")
    private String patentee;
    @ApiModelProperty("授权公告日")
    private Timestamp authorizationTime;
    @ApiModelProperty("专利证书照片")
    private String patentPhoto;
    @ApiModelProperty("产生专利项目")
    @OneToOne(targetEntity = Project.class)
    private Project project;


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

    public String getPatentPhoto() {
        return patentPhoto;
    }

    public void setPatentPhoto(String patentPhoto) {
        this.patentPhoto = patentPhoto;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
