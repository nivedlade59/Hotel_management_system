package com.nived.hotel.hotelmanageapiserver.repository;

import com.nived.hotel.hotelmanageapiserver.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository
        extends JpaRepository<ActivityLog, Integer> {

    List<ActivityLog> findAllByOrderByLoginTimeDesc();
}