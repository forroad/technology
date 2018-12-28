package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Deal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DealDao extends JpaRepository<Deal,Integer>, JpaSpecificationExecutor<Deal> {
}
