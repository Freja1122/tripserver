package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
