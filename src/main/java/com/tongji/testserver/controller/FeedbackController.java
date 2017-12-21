package com.tongji.testserver.controller;

import com.tongji.testserver.domain.Feedback;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
import com.tongji.testserver.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController{
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public Response save(Feedback feedback) {
        try {
        feedbackService.saveFeeddback(feedback,getUser());
        } catch (Exception e) {
            logger.error("feedback failed, ", e);
            return result(ExceptionMsg.FAILED);
        }
        return result();
    }
}
