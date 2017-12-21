package com.tongji.testserver.controller;


import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController{
	
	@Autowired
	private NoticeRepository noticeRepository;


	@RequestMapping(value="/getNoticeNum")
	@LoggerManage(description="获取新消息数量")
	public ResponseData getNoticeNum(){
		Map<String,Long> result = new HashMap<String, Long>();
		Long newAtMeCount = noticeRepository.countByUserIdAndTypeAndReaded(getUser(), "at", "unread");
		Long newCommentMeCount = noticeRepository.countByUserIdAndTypeAndReaded(getUser(), "comment", "unread");
//		Long newPraiseMeCount = noticeRepository.countPraiseByUserIdAndReaded(getUserId(), "unread");
		Long newLetterNotice = noticeRepository.countByUserIdAndTypeAndReaded(getUser(),"letter","unread");
		result.put("newAtMeCount",newAtMeCount);
		result.put("newCommentMeCount",newCommentMeCount);
//		result.put("newPraiseMeCount",newPraiseMeCount);
		result.put("newLetterNotice",newLetterNotice);
		return new ResponseData(ExceptionMsg.SUCCESS,result);
	}

}
