package com.example.venue.config;

import com.example.venue.model.Booking;
import com.example.venue.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Bean
    public CommandLineRunner initBookingData() {
        return args -> {
            // 检查数据库是否为空
            List<Booking> bookings = bookingRepository.findAll();
            logger.info("当前数据库中有 {} 条预约记录", bookings.size());
            
            // 检查是否有7月28日的数据
            List<Booking> july28Bookings = bookingRepository.findByDate("2025-07-28");
            
            if (july28Bookings.isEmpty()) {
                logger.info("初始化2025-07-28的预约数据");
                
                // 创建预约
                Booking booking1 = new Booking();
                booking1.setDate("2025-07-28");
                booking1.setPeriod("morning");
                booking1.setId(1L);
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
                booking2.setId(2L);
                booking2.setBranch("二支行");
                booking2.setTime("09:00-11:30");
                booking2.setContact("李经理");
                booking2.setPhone("13800000002");
                booking2.setAttendees(8);
                
                Booking booking3 = new Booking();
                booking3.setDate("2025-07-28");
                booking3.setPeriod("morning");
                booking3.setId(5L);
                booking3.setBranch("东山支行");
                booking3.setTime("10:00-12:00");
                booking3.setContact("王经理");
                booking3.setPhone("13800000003");
                booking3.setAttendees(6);
                
                // 保存预约
                bookingRepository.save(booking1);
                bookingRepository.save(booking2);
                bookingRepository.save(booking3);
                
                logger.info("成功初始化3条7月28日的预约数据");
            } else {
                logger.info("数据库中已有 {} 条7月28日的预约数据", july28Bookings.size());
            }
        };
    }
}