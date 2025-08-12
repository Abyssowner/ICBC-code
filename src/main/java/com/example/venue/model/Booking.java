package com.example.venue.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data // 如果Lombok不工作，可以删除这个注解并使用手动添加的getter/setter
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private Integer roomId;
    
    @NotBlank
    private String date;
    
    @NotBlank
    private String period; // "morning" 或 "afternoon"
    
    @NotBlank
    private String time; // 具体时间段，如 "08:30-10:30"
    
    @NotBlank
    private String contact; // 联系人
    
    @NotBlank
    private String phone; // 联系电话
    
    @NotNull
    private Integer attendees; // 参与人数
    
    // 新增三类人数字段
    private Integer customerAttendees; // 客户人数
    
    private Integer internalAttendees; // 行内人员人数
    
    private Integer thirdPartyAttendees; // 三方机构人数
    
    @NotBlank
    private String branch; // 分行名称
    
    private String theme; // 活动主题（可选）
    
    private String notes; // 备注信息（可选）
    
    // 手动添加getter方法
    public Long getId() {
        return id;
    }
    
    public Integer getRoomId() {
        return roomId;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getPeriod() {
        return period;
    }
    
    public String getTime() {
        return time;
    }
    
    public String getContact() {
        return contact;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public Integer getAttendees() {
        return attendees;
    }
    
    public Integer getCustomerAttendees() {
        return customerAttendees;
    }
    
    public Integer getInternalAttendees() {
        return internalAttendees;
    }
    
    public Integer getThirdPartyAttendees() {
        return thirdPartyAttendees;
    }
    
    public String getBranch() {
        return branch;
    }
    
    public String getTheme() {
        return theme;
    }
    
    public String getNotes() {
        return notes;
    }
    
    // 手动添加setter方法
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setPeriod(String period) {
        this.period = period;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setAttendees(Integer attendees) {
        this.attendees = attendees;
    }
    
    public void setCustomerAttendees(Integer customerAttendees) {
        this.customerAttendees = customerAttendees;
    }
    
    public void setInternalAttendees(Integer internalAttendees) {
        this.internalAttendees = internalAttendees;
    }
    
    public void setThirdPartyAttendees(Integer thirdPartyAttendees) {
        this.thirdPartyAttendees = thirdPartyAttendees;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
}