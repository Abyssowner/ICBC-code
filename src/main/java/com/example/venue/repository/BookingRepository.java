package com.example.venue.repository;

import com.example.venue.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.date = :date")
    List<Booking> findByDate(@Param("date") String date);
    
    // 增加一个更灵活的查询方法
    @Query("SELECT b FROM Booking b WHERE b.date = :date AND b.period = :period")
    List<Booking> findByDateAndPeriod(@Param("date") String date, @Param("period") String period);

    // 根据联系人查询预约
    List<Booking> findByContact(String contact);
    
    // 获取所有联系人的列表(去重)
    @Query("SELECT DISTINCT b.contact FROM Booking b ORDER BY b.contact")
    List<String> findAllContacts();
    List<Booking> findByDateAndRoomId(String date, int roomId);
    List<Booking> findByRoomId(int roomId);
    List<Booking> findByThemeContaining(String theme);
}