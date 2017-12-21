package com.tongji.testserver.service;

import com.tongji.testserver.domain.Feedback;
import com.tongji.testserver.domain.User;

import java.util.List;

/**
 * Created by chenzhimin on 2017/2/23.
 */
public interface FeedbackService {

    public void saveFeeddback(Feedback feedback, User user);

    public Feedback getFeeddback(long id);

    public void setFeedbackAdvice(long id, String feedbackAdvice);

    public void setFeedbackName(long id, String feedbackName);

    public void setSolution(long id, String solution);

    public void setSolved(long id, String solved);

    public List<Feedback> getAllFeedback(long userId);

    public void deleteAllFeedback(long userId);
}
