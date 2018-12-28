package com.ycjw.technology.model.request.award;

import com.ycjw.technology.model.award.Award;

import java.sql.Timestamp;

public class ModifyAward {
    private int id;
    private String awardRank;
    private Timestamp awardTime;
    private Award.AwardLevel awardLevel;
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Award.AwardLevel getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(Award.AwardLevel awardLevel) {
        this.awardLevel = awardLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
