package com.ycjw.technology.repository.test;

import com.ycjw.technology.model.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao extends JpaRepository<Test,Integer> {
    Test findByName(String name);
}
