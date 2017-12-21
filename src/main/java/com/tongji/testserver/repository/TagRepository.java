package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<Tag, Long> {
}
