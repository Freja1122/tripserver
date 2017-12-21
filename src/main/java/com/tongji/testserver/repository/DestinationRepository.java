package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Time;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Destination findByLatitudeAndLongitude(double latitude, double longitude);
    Destination findById(long id);


}
