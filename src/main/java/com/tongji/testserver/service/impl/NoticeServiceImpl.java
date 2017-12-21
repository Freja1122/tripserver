package com.tongji.testserver.service.impl;


import com.tongji.testserver.domain.Notice;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.view.CollectSummary;
import com.tongji.testserver.domain.view.CollectView;
import com.tongji.testserver.repository.NoticeRepository;
import com.tongji.testserver.repository.UserRepository;
import com.tongji.testserver.service.NoticeService;
import com.tongji.testserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 保存消息通知
	 * @param type
	 */
	public void saveNotice(Path path, String type, User user){

		Notice notice = new Notice();
		if(path!=null){
			notice.setPath(path);
		}
		notice.setReaded("unread");
		notice.setType(type);
		notice.setUser(user);
		notice.setCreateTime(DateUtils.getCurrentTime());
		noticeRepository.save(notice);
	}

	/**
	 * 展示消息通知
	 * @param type
	 * @param userId
	 * @param pageable
	 */
	@Override
	public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CollectView> views = noticeRepository.findViewByUserIdAndType(userId, type, pageable);
		return convertCollect(views, type);
	}

	private List<CollectSummary> convertCollect(Page<CollectView> views, String type) {
		List<CollectSummary> summarys=new ArrayList<CollectSummary>();
		for (CollectView view : views) {
			CollectSummary summary=new CollectSummary(view);
			if("at".equals(type)){
				summary.setCollectTime(DateUtils.getTimeFormatText(view.getLastModifyTime())+" at了你");
			}
		}
		return summarys;
	}


	@Override
	public void saveNotice(Path path, User user, Date noticeTime) {

	}

	@Override
	public void setDescription(long id, String description) {

	}

	@Override
	public void setReaded(long id, String readed) {

	}

	@Override
	public void setType(long id, String type) {

	}

	@Override
	public void setNoticeTime(long id, String noticeTime) {

	}

	@Override
	public List<Notice> getAllNotice(User user) {
		return null;
	}

	@Override
	public Notice getNotice(User user, long id) {
		return null;
	}

	@Override
	public void deleteNotice(User user, long noticeId) {

	}
}
