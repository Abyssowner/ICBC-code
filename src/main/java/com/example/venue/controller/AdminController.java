package com.example.venue.controller;

import com.example.venue.model.Booking;
import com.example.venue.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;
    
    /**
     * 删除指定联系人的所有预约
     */
    @DeleteMapping("/bookings/contact/{contactName}")
    public ResponseEntity<Map<String, Object>> deleteBookingsByContact(@PathVariable String contactName) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 查找指定联系人的所有预约
            List<Booking> bookingsToDelete = bookingRepository.findByContact(contactName);
            
            if (bookingsToDelete.isEmpty()) {
                result.put("success", false);
                result.put("message", "没有找到联系人 '" + contactName + "' 的预约记录");
                return ResponseEntity.ok(result);
            }
            
            // 记录要删除的预约ID
            List<Long> deletedIds = bookingsToDelete.stream()
                .map(Booking::getId)
                .toList();
            
            // 删除预约
            bookingRepository.deleteAll(bookingsToDelete);
            
            result.put("success", true);
            result.put("message", "成功删除 " + bookingsToDelete.size() + " 条 '" + contactName + "' 的预约记录");
            result.put("count", bookingsToDelete.size());
            result.put("deletedIds", deletedIds);
            result.put("deletedBookings", bookingsToDelete);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除预约失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }
    
    /**
     * 获取所有联系人列表
     */
    @GetMapping("/contacts")
    public ResponseEntity<Map<String, Object>> getAllContacts() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<String> contacts = bookingRepository.findAllContacts();
            
            result.put("success", true);
            result.put("contacts", contacts);
            result.put("count", contacts.size());
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取联系人列表失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }
}