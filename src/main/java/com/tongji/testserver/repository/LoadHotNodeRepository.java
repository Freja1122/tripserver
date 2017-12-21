package com.tongji.testserver.repository;

import com.tongji.testserver.domain.LoadHotNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadHotNodeRepository extends JpaRepository<LoadHotNode, Long> {
    LoadHotNode findById(Long id);
}
