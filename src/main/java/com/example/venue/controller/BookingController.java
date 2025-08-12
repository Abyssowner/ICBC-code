package com.example.venue.controller;

import com.example.venue.model.Booking;
import com.example.venue.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {
    
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    
    @Autowired
    private BookingRepository bookingRepository;
    
    // 确保明确指定为GET方法
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Booking>> getBookingsByDate(@PathVariable String date) {
        logger.info("Fetching bookings for date: {}", date);
        
        List<Booking> bookings = bookingRepository.findByDate(date);
        logger.info("Found {} bookings for date {}", bookings.size(), date);
        
        // 打印每条预约信息进行调试
        for (Booking booking : bookings) {
            logger.info("Booking: id={}, date={}, period={}, roomId={}, theme={}, totalAttendees={}, customerAttendees={}, internalAttendees={}, thirdPartyAttendees={}", 
                       booking.getId(), booking.getDate(), booking.getPeriod(), booking.getRoomId(),
                       booking.getTheme(), booking.getAttendees(),
                       booking.getCustomerAttendees(), booking.getInternalAttendees(), booking.getThirdPartyAttendees());
        }
        
        return ResponseEntity.ok(bookings);
    }
    
    // 添加日期和时段组合查询
    @GetMapping("/date/{date}/period/{period}")
    public ResponseEntity<List<Booking>> getBookingsByDateAndPeriod(
            @PathVariable String date, 
            @PathVariable String period) {
        
        logger.info("Fetching bookings for date: {} and period: {}", date, period);
        
        List<Booking> bookings = bookingRepository.findByDateAndPeriod(date, period);
        logger.info("Found {} bookings for date {} and period {}", 
                  bookings.size(), date, period);
        
        return ResponseEntity.ok(bookings);
    }
    
    // 提交预约 - 确保使用 POST 方法，并记录详细人数信息
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        logger.info("Creating new booking: {}", booking);
        
        // 确保人数字段不为null
        if (booking.getCustomerAttendees() == null) {
            booking.setCustomerAttendees(0);
        }
        
        if (booking.getInternalAttendees() == null) {
            booking.setInternalAttendees(0);
        }
        
        if (booking.getThirdPartyAttendees() == null) {
            booking.setThirdPartyAttendees(0);
        }
        
        // 验证主题字段
        if (booking.getTheme() == null || booking.getTheme().trim().isEmpty()) {
            booking.setTheme("未设置主题");
        }
        
        // 确保总人数与分类人数一致
        int totalAttendees = booking.getCustomerAttendees() + booking.getInternalAttendees() + booking.getThirdPartyAttendees();
        if (booking.getAttendees() == null || booking.getAttendees() != totalAttendees) {
            booking.setAttendees(totalAttendees);
            logger.info("Adjusted total attendees to match sum of categories: {}", totalAttendees);
        }
        
        logger.info("Saving booking with theme: '{}', customers: {}, internal: {}, thirdParty: {}, total: {}", 
                   booking.getTheme(), booking.getCustomerAttendees(), 
                   booking.getInternalAttendees(), booking.getThirdPartyAttendees(),
                   booking.getAttendees());
        
        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(savedBooking);
    }
    
    // 调试用 - 获取所有预约
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        logger.info("Returning all {} bookings", allBookings.size());
        return ResponseEntity.ok(allBookings);
    }
    
    // 添加根据主题搜索的功能
    @GetMapping("/search/theme")
    public ResponseEntity<List<Booking>> searchByTheme(@RequestParam String keyword) {
        logger.info("Searching bookings with theme containing: {}", keyword);
        List<Booking> matchingBookings = bookingRepository.findByThemeContaining(keyword);
        return ResponseEntity.ok(matchingBookings);
    }
    
    // 专门用于测试2025-07-28的预约，增加了新字段信息
    @GetMapping("/test-july-28")
    public ResponseEntity<Map<String, Object>> testJuly28() {
        String date = "2025-07-28";
        
        // 获取所有预约
        List<Booking> allBookings = bookingRepository.findAll();
        
        // 过滤出7月28日的预约
        List<Booking> dateBookings = allBookings.stream()
            .filter(b -> b.getDate() != null && b.getDate().equals(date))
            .collect(Collectors.toList());
        
        // 如果没有找到任何预约，添加测试数据
        if (dateBookings.isEmpty()) {
            logger.info("No bookings found for 2025-07-28, adding test data");
            
            // 添加示例数据
            Booking booking1 = new Booking();
            booking1.setRoomId(1);
            booking1.setDate("2025-07-28");
            booking1.setPeriod("morning");
            booking1.setTime("09:00-10:00");
            booking1.setContact("张经理");
            booking1.setPhone("13800000001");
            booking1.setAttendees(30);
            booking1.setBranch("一支行");
            booking1.setNotes("需要投影设备");
            booking1.setTheme("团队建设会议");
            booking1.setCustomerAttendees(20);
            booking1.setInternalAttendees(5);
            booking1.setThirdPartyAttendees(5);
            
            Booking booking2 = new Booking();
            booking2.setRoomId(2);
            booking2.setDate("2025-07-28");
            booking2.setPeriod("morning");
            booking2.setTime("10:00-11:00");
            booking2.setContact("李经理");
            booking2.setPhone("13800000002");
            booking2.setAttendees(8);
            booking2.setBranch("二支行");
            booking2.setNotes("");
            booking2.setTheme("客户洽谈");
            booking2.setCustomerAttendees(2);
            booking2.setInternalAttendees(6);
            booking2.setThirdPartyAttendees(0);
            
            Booking booking3 = new Booking();
            booking3.setRoomId(5);
            booking3.setDate("2025-07-28");
            booking3.setPeriod("morning");
            booking3.setTime("11:00-12:00");
            booking3.setContact("王经理");
            booking3.setPhone("13800000003");
            booking3.setAttendees(6);
            booking3.setBranch("东山支行");
            booking3.setNotes("");
            booking3.setTheme("内部会议");
            booking3.setCustomerAttendees(0);
            booking3.setInternalAttendees(6);
            booking3.setThirdPartyAttendees(0);
            
            bookingRepository.save(booking1);
            bookingRepository.save(booking2);
            bookingRepository.save(booking3);
            
            // 重新获取预约
            dateBookings = bookingRepository.findByDate(date);
        }
        
        // 区分上午和下午
        List<Booking> morningBookings = dateBookings.stream()
            .filter(b -> "morning".equals(b.getPeriod()))
            .collect(Collectors.toList());
        
        List<Booking> afternoonBookings = dateBookings.stream()
            .filter(b -> "afternoon".equals(b.getPeriod()))
            .collect(Collectors.toList());
        
        // 准备响应数据
        Map<String, Object> result = new HashMap<>();
        result.put("date", date);
        result.put("currentUser", "Abyssowner");
        result.put("currentTime", "2025-08-11 01:41:03");
        result.put("totalBookings", allBookings.size());
        result.put("dateBookingsCount", dateBookings.size());
        result.put("dateBookings", dateBookings);
        result.put("morningCount", morningBookings.size());
        result.put("morningBookings", morningBookings);
        result.put("afternoonCount", afternoonBookings.size());
        result.put("afternoonBookings", afternoonBookings);
        
        return ResponseEntity.ok(result);
    }
}