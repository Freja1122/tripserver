package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Path;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PathRepository extends JpaRepository<Path, Long> {
    Path findById(long id);
}
