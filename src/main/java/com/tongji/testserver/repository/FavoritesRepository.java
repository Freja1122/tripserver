package com.tongji.testserver.repository;


import com.tongji.testserver.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

	List<Favorites> findByUserId(Long userId);
	
	List<Favorites> findByUserIdOrderByIdDesc(Long userId);
	
	Favorites findByUserIdAndName(Long userId, String name);

	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update Favorites f set f.count=(f.count+1),f.lastModifyTime =:lastModifyTime where f.id =:id")
	void increaseCountById(@Param("id") long id, @Param("lastModifyTime") Long lastModifyTime);

	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update Favorites f set f.count=(f.count-1),f.lastModifyTime =:lastModifyTime where f.id =:id")
	void reduceCountById(@Param("id") long id, @Param("lastModifyTime") Long lastModifyTime);

	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update Favorites set name=:name ,lastModifyTime=:lastModifyTime where id=:id")
	void updateNameById(@Param("id") long id, @Param("lastModifyTime") Long lastModifyTime, @Param("name") String name);

	@Query("select id from Favorites where name=?1")
	List<Long> findIdByName(String name);
}