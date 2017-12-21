package com.tongji.testserver.repository;

import com.tongji.testserver.domain.PsgHotNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsgHotNodeRepository extends JpaRepository<PsgHotNode, Long> {
    PsgHotNode findById(long id);
}
