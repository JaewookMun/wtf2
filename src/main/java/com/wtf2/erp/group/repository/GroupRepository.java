package com.wtf2.erp.group.repository;

import com.wtf2.erp.group.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByCode(String code);
}