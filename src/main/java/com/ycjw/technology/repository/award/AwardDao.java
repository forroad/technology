package com.ycjw.technology.repository.award;

import com.ycjw.technology.model.award.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardDao extends JpaRepository<Award,Integer> {
}
