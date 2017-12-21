package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Notice;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.view.CollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<CollectView> findViewByUserIdAndType(Long userId, String type, Pageable pageable);

	public String baseSql="select c.id as id,c.title as title, c.type as type, c.userId as userId, "
			+ "c.description as description,c.lastModifyTime as lastModifyTime, "
			+ "u.userName as userName,u.profilePicture as profilePicture"
			+ "from Notice n,Path c,User u WHERE n.pathId=c.id and c.userId=u.id";

//	@Query(baseSql+ " and n.userId=?1 and n.type=?2")
//    Page<CollectView> findViewByUserIdAndType(Long userId, String type, Pageable pageable);

	Long countByUserIdAndTypeAndReaded(User user, String type, String readed);


	@Transactional
	@Modifying
	@Query("update Notice n set n.readed = :readed where n.user = :user and n.type = :type and n.readed='unread'")
	int updateReadedByUserId(String readed, User user, String type);

}