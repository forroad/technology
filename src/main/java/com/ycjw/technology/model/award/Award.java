package com.ycjw.technology.model.award;

import com.ycjw.technology.model.user.Mentor;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_award")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("奖项ID")
    private int id;
    @ApiModelProperty("获奖者ID")
    @OneToOne(targetEntity = Mentor.class)
    private Mentor mentor;
    @ApiModelProperty("获奖排名")
    private String awardRank;
    @ApiModelProperty("获奖时间")
    private Timestamp awardTime;
    @ApiModelProperty("获奖等级")
    private AwardLevel awardLevel;
    @ApiModelProperty("所属单位")
    private String department;


    public enum AwardLevel{
        school,province,country,world
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public String getAwardRank() {
        return awardRank;
    }

    public void setAwardRank(String awardRank) {
        this.awardRank = awardRank;
    }

    public Timestamp getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(Timestamp awardTime) {
        this.awardTime = awardTime;
    }

    public AwardLevel getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(AwardLevel awardLevel) {
        this.awardLevel = awardLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
