package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultDao extends JpaRepository<Result,Integer> {
}
