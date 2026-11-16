package com.nived.hotel.hotelmanageapiserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nived.hotel.hotelmanageapiserver.entity.ActivityLog;
import com.nived.hotel.hotelmanageapiserver.service.ActivityLogService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
@CrossOrigin(origins = "http://localhost:5173")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(
            ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    // Get all activity logs, newest first
    @GetMapping
    public List<ActivityLog> getAllActivityLogs() {
        return activityLogService.getAllActivityLogs();
    }
}