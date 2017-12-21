package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.PsgHotFavorites;
import com.tongji.testserver.domain.PsgHotNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsgHotFavoritesRepository  extends JpaRepository<PsgHotFavorites, Long> {

}
