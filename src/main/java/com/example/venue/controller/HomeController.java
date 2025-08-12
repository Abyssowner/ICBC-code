package com.example.venue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "running");
        response.put("message", "Venue Booking API is running");
        response.put("endpoints", new String[] {
            "/api/bookings/all", 
            "/api/bookings/date/{date}", 
            "/api/rooms"
        });
        response.put("time", new java.util.Date());
        return response;
    }
    
    @GetMapping("/api")
    public Map<String, Object> api() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "running");
        response.put("message", "API base path - use specific endpoints");
        response.put("availableEndpoints", new String[] {
            "/api/bookings/all", 
            "/api/bookings/date/{date}", 
            "/api/rooms"
        });
        return response;
    }
}