package com.tongji.testserver.service.impl;

import com.tongji.testserver.domain.Feedback;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.repository.FeedbackRepository;
import com.tongji.testserver.service.FeedbackService;
import com.tongji.testserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenzhimin on 2017/2/23.
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void saveFeeddback(Feedback feedback, User user) {
        if (user!=null){
            feedback.setUser(user);
        }
        feedback.setCreateTime(DateUtils.getCurrentTime());
        feedback.setLastModifyTime(DateUtils.getCurrentTime());
        feedbackRepository.save(feedback);
    }
//
//    @Override
//    public Feedback saveFeeddback(Feedback feedback, User user) {
//        return null;
//    }

    @Override
    public Feedback getFeeddback(long id) {
        return null;
    }

    @Override
    public void setFeedbackAdvice(long id, String feedbackAdvice) {

    }

    @Override
    public void setFeedbackName(long id, String feedbackName) {

    }

    @Override
    public void setSolution(long id, String solution) {

    }

    @Override
    public void setSolved(long id, String solved) {

    }

    @Override
    public List<Feedback> getAllFeedback(long userId) {
        return null;
    }

    @Override
    public void deleteAllFeedback(long userId) {

    }
}
