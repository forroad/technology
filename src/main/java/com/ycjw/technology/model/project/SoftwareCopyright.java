package com.ycjw.technology.model.project;

import com.ycjw.technology.model.request.project.AddSoftwareCopyright;
import com.ycjw.technology.model.user.Mentor;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_software")
public class SoftwareCopyright {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("软件著作权ID")
    private int id;
    @ApiModelProperty("软件名称")
    private String name;
    @ApiModelProperty("著作权人")
    private String copyrightOwner;
    @ApiModelProperty("开发完成日期")
    private Timestamp finishTime;
    @ApiModelProperty("首次发表日期")
    private Timestamp publishTime;
    @ApiModelProperty("授权取得方式")
    private String AuthorizationWay;
    @ApiModelProperty("权利范围")
    private String RightRange;
    @ApiModelProperty("登记号")
    private String softwareId;
    @ApiModelProperty("软件著作权照片")
    private String softwareCopyrightPhoto;
    @ApiModelProperty("产生软件著作权项目Id")
    private int projectId;

    public SoftwareCopyright() {
    }

    public SoftwareCopyright(AddSoftwareCopyright addSoftwareCopyright) {
        this.name = addSoftwareCopyright.getName();
        this.copyrightOwner = addSoftwareCopyright.getCopyrightOwner();
        this.finishTime = addSoftwareCopyright.getFinishTime();
        this.publishTime = addSoftwareCopyright.getPublishTime();
        this.AuthorizationWay = addSoftwareCopyright.getAuthorizationWay();
        this.RightRange = addSoftwareCopyright.getRightRange();
        this.softwareId = addSoftwareCopyright.getSoftwareId();
        this.projectId = addSoftwareCopyright.getProjectId();
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

    public String getSoftwareCopyrightPhoto() {
        return softwareCopyrightPhoto;
    }

    public void setSoftwareCopyrightPhoto(String softwareCopyrightPhoto) {
        this.softwareCopyrightPhoto = softwareCopyrightPhoto;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
