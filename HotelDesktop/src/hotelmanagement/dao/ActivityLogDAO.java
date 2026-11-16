/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.dao;

/**
 *
 * @author Nived
 */

import hotelmanagement.api.ApiClient;
import hotelmanagement.model.ActivityLog;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;

public class ActivityLogDAO {

    private final Gson gson = new Gson();

    public List<ActivityLog> getAllLogs() {

        List<ActivityLog> logs = new ArrayList<>();

        try {
            String response = ApiClient.get("/api/activity-logs");

            if (response == null || response.isBlank()) {
                return logs;
            }

            JsonArray array = JsonParser.parseString(response)
                    .getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {

                JsonObject json = array.get(i).getAsJsonObject();

                ActivityLog log = new ActivityLog();

                log.setLogId(json.get("logId").getAsInt());
                log.setUsername(json.get("username").getAsString());

                String loginTime = json.get("loginTime").getAsString();

                log.setLoginTime(
                        Timestamp.valueOf(LocalDateTime.parse(loginTime))
                );

                logs.add(log);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return logs;
    }
}