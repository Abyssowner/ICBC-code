package com.example.venue.controller;

import com.example.venue.model.Booking;
import com.example.venue.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/init")
public class DataInitController {

    @Autowired
    private BookingRepository bookingRepository;
    
    @GetMapping("/all-data")
    public ResponseEntity<Map<String, Object>> initAllData() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<Booking> newBookings = new ArrayList<>();
            
            // 2025-07-28 数据
            newBookings.add(createBooking("2025-07-28", "morning", 1, "一支行", "08:30-12:00", "张经理", "13800000001", 30, "团队建设会议", "需要投影设备"));
            newBookings.add(createBooking("2025-07-28", "morning", 2, "二支行", "09:00-11:30", "李经理", "13800000002", 8, "", ""));
            newBookings.add(createBooking("2025-07-28", "morning", 5, "东山支行", "10:00-12:00", "王经理", "13800000003", 6, "", ""));
            newBookings.add(createBooking("2025-07-28", "afternoon", 3, "荔湾支行", "14:00-17:00", "赵经理", "13800000004", 12, "", ""));
            
            // 2025-07-29 数据
            newBookings.add(createBooking("2025-07-29", "morning", 4, "天河支行", "09:00-11:00", "钱经理", "13800000005", 4, "", ""));
            newBookings.add(createBooking("2025-07-29", "afternoon", 6, "白云支行", "13:30-16:30", "孙经理", "13800000006", 8, "", ""));
            
            // 2025-07-30 数据
            newBookings.add(createBooking("2025-07-30", "morning", 7, "海珠支行", "08:30-11:30", "周经理", "13800000007", 10, "", ""));
            newBookings.add(createBooking("2025-07-30", "afternoon", 8, "越秀支行", "14:00-16:00", "吴经理", "13800000008", 25, "", ""));
            
            // 2025-07-31 数据 - 上午全部预约
            newBookings.add(createBooking("2025-07-31", "morning", 1, "一支行", "08:30-12:00", "张经理", "13800000001", 30, "季度工作会议", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 2, "二支行", "09:00-11:30", "李经理", "13800000002", 8, "", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 3, "三支行", "10:00-12:00", "王经理", "13800000003", 12, "", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 4, "荔湾支行", "09:00-11:00", "赵经理", "13800000004", 4, "", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 5, "天河支行", "08:30-12:00", "钱经理", "13800000005", 9, "", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 6, "白云支行", "09:30-11:30", "孙经理", "13800000006", 8, "", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 7, "海珠支行", "10:00-12:00", "周经理", "13800000007", 10, "", ""));
            newBookings.add(createBooking("2025-07-31", "morning", 8, "越秀支行", "08:30-11:30", "吴经理", "13800000008", 25, "", ""));
            
            // 保存所有预约数据
            List<Booking> savedBookings = bookingRepository.saveAll(newBookings);
            
            result.put("success", true);
            result.put("message", "成功初始化所有预定义数据");
            result.put("count", savedBookings.size());
            result.put("bookings", savedBookings);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/july28")
    public ResponseEntity<Map<String, Object>> initJuly28Data() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<Booking> newBookings = new ArrayList<>();
            
            // 2025-07-28 数据
            newBookings.add(createBooking("2025-07-28", "morning", 1, "一支行", "08:30-12:00", "张经理", "13800000001", 30, "团队建设会议", "需要投影设备"));
            newBookings.add(createBooking("2025-07-28", "morning", 2, "二支行", "09:00-11:30", "李经理", "13800000002", 8, "", ""));
            newBookings.add(createBooking("2025-07-28", "morning", 5, "东山支行", "10:00-12:00", "王经理", "13800000003", 6, "", ""));
            newBookings.add(createBooking("2025-07-28", "afternoon", 3, "荔湾支行", "14:00-17:00", "赵经理", "13800000004", 12, "", ""));
            
            // 保存预约
            List<Booking> savedBookings = bookingRepository.saveAll(newBookings);
            
            result.put("success", true);
            result.put("message", "成功初始化2025-07-28数据");
            result.put("count", savedBookings.size());
            result.put("bookings", savedBookings);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return ResponseEntity.ok(result);
    }
    
    // 清除重复数据
    @GetMapping("/clean-data")
    public ResponseEntity<Map<String, Object>> cleanDuplicateData() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 获取所有数据进行检查
            List<Booking> allBookings = bookingRepository.findAll();
            Map<String, List<Booking>> dateRoomPeriodMap = new HashMap<>();
            List<Booking> duplicatesToRemove = new ArrayList<>();
            
            // 根据日期+房间+时段分组，找出重复项
            for (Booking booking : allBookings) {
                String key = booking.getDate() + "_" + booking.getRoomId() + "_" + booking.getPeriod();
                
                if (!dateRoomPeriodMap.containsKey(key)) {
                    dateRoomPeriodMap.put(key, new ArrayList<>());
                }
                
                dateRoomPeriodMap.get(key).add(booking);
            }
            
            // 标记需要删除的重复项
            for (List<Booking> bookingList : dateRoomPeriodMap.values()) {
                if (bookingList.size() > 1) {
                    // 保留第一条，标记其余的删除
                    for (int i = 1; i < bookingList.size(); i++) {
                        duplicatesToRemove.add(bookingList.get(i));
                    }
                }
            }
            
            // 删除重复项
            bookingRepository.deleteAll(duplicatesToRemove);
            
            result.put("success", true);
            result.put("message", "成功清理重复数据");
            result.put("removedCount", duplicatesToRemove.size());
            result.put("remainingCount", allBookings.size() - duplicatesToRemove.size());
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return ResponseEntity.ok(result);
    }
    
    // 修复数据一致性问题
    @GetMapping("/fix-consistency")
    public ResponseEntity<Map<String, Object>> fixDataConsistency() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<Booking> allBookings = bookingRepository.findAll();
            int fixedCount = 0;
            
            for (Booking booking : allBookings) {
                boolean needsUpdate = false;
                
                // 检查时间和时段是否一致
                if (booking.getTime() != null && !booking.getTime().isEmpty()) {
                    int hour = Integer.parseInt(booking.getTime().split(":")[0]);
                    
                    // 确保时段与时间匹配
                    if (hour < 12 && !"morning".equals(booking.getPeriod())) {
                        booking.setPeriod("morning");
                        needsUpdate = true;
                        fixedCount++;
                    } else if (hour >= 12 && !"afternoon".equals(booking.getPeriod())) {
                        booking.setPeriod("afternoon");
                        needsUpdate = true;
                        fixedCount++;
                    }
                }
                
                if (needsUpdate) {
                    bookingRepository.save(booking);
                }
            }
            
            result.put("success", true);
            result.put("message", "成功修复数据一致性问题");
            result.put("fixedCount", fixedCount);
        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return ResponseEntity.ok(result);
    }
    
    // 辅助方法：创建预约对象 - 修复了参数类型并修正了setter方法
    private Booking createBooking(String date, String period, Integer roomId, String branch, 
                                String time, String contact, String phone, 
                                int attendees, String theme, String notes) {
        Booking booking = new Booking();
        booking.setDate(date);
        booking.setPeriod(period);
        booking.setRoomId(roomId); // 修正：设置roomId而不是id
        booking.setBranch(branch);
        booking.setTime(time);
        booking.setContact(contact);
        booking.setPhone(phone);
        booking.setAttendees(attendees);
        booking.setTheme(theme);
        booking.setNotes(notes);
        return booking;
    }
}