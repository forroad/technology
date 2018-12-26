package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Patent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentDao extends JpaRepository<Patent,Integer> {
}
