package com.example.venue.controller;

import com.example.venue.model.Booking;
import com.example.venue.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private BookingRepository bookingRepository;
    
    @GetMapping("/july28")
    public Map<String, Object> getJuly28Data() {
        Map<String, Object> result = new HashMap<>();
        
        List<Booking> allBookings = bookingRepository.findAll();
        List<Booking> july28Bookings = bookingRepository.findByDate("2025-07-28");
        
        result.put("totalBookings", allBookings.size());
        result.put("july28BookingsCount", july28Bookings.size());
        result.put("july28Bookings", july28Bookings);
        
        return result;
    }
    
    @GetMapping("/init-july28")
    public Map<String, Object> initJuly28Data() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 删除现有的7月28日数据
            List<Booking> existingBookings = bookingRepository.findByDate("2025-07-28");
            if (!existingBookings.isEmpty()) {
                bookingRepository.deleteAll(existingBookings);
            }
            
            // 创建新的预约
            Booking booking1 = new Booking();
            booking1.setDate("2025-07-28");
            booking1.setPeriod("morning");
            booking1.setRoomId(1);
            booking1.setBranch("一支行");
            booking1.setTime("08:30-12:00");
            booking1.setContact("张经理");
            booking1.setPhone("13800000001");
            booking1.setAttendees(30);
            booking1.setTheme("团队建设会议");
            booking1.setNotes("需要投影设备");
            
            Booking booking2 = new Booking();
            booking2.setDate("2025-07-28");
            booking2.setPeriod("morning");
            booking2.setRoomId(2);
            booking2.setBranch("二支行");
            booking2.setTime("09:00-11:30");
            booking2.setContact("李经理");
            booking2.setPhone("13800000002");
            booking2.setAttendees(8);
            
            Booking booking3 = new Booking();
            booking3.setDate("2025-07-28");
            booking3.setPeriod("morning");
            booking3.setRoomId(5);
            booking3.setBranch("东山支行");
            booking3.setTime("10:00-12:00");
            booking3.setContact("王经理");
            booking3.setPhone("13800000003");
            booking3.setAttendees(6);
            
            // 保存预约
            bookingRepository.save(booking1);
            bookingRepository.save(booking2);
            bookingRepository.save(booking3);
            
            result.put("success", true);
            result.put("message", "Successfully initialized July 28 data");
            result.put("count", 3);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
}