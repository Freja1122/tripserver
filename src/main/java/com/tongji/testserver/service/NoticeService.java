package com.tongji.testserver.service;

import com.tongji.testserver.domain.Notice;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.view.CollectSummary;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface NoticeService {
	
	public void saveNotice(Path path, User user, Date noticeTime);

	public void setDescription(long id, String description);

	public void setReaded(long id, String readed);

	public void setType(long id, String type);

	public void setNoticeTime(long id, String noticeTime);

	public List<Notice> getAllNotice(User user);

	public Notice getNotice(User user, long id);

	public void deleteNotice(User user, long noticeId);

	public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable);

}
