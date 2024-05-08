package com.wtf2.erp.dept.repository;

import com.wtf2.erp.dept.domain.Dept;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept, Long> {

    @EntityGraph(attributePaths = "parent")
    @Override
    List<Dept> findAll();
}
