package com.nived.hotel.hotelmanageapiserver.service;

import com.nived.hotel.hotelmanageapiserver.entity.ActivityLog;
import com.nived.hotel.hotelmanageapiserver.repository.ActivityLogRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(
            ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    // Get all activity logs, newest first
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogRepository
                .findAllByOrderByLoginTimeDesc();
    }

    // Record a successful login
    public ActivityLog recordLogin(String username) {

        ActivityLog activityLog = new ActivityLog();
        activityLog.setUsername(username);

        // MySQL supplies login_time using CURRENT_TIMESTAMP
        return activityLogRepository.save(activityLog);
    }
}