package com.example.venue.controller;

import com.example.venue.model.Room;
import com.example.venue.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        
        // 如果数据库中没有房间数据，则初始化一些默认房间
        if (rooms.isEmpty()) {
            rooms = initializeRooms();
        }
        
        // 转换图片路径为绝对URL
        for (Room room : rooms) {
            setAbsoluteImageUrl(room);
        }
        
        return ResponseEntity.ok(rooms);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomRepository.findById(id)
                .map(room -> {
                    setAbsoluteImageUrl(room);
                    return ResponseEntity.ok(room);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // 为房间图片设置绝对URL - 修改为void返回类型，直接修改传入的Room对象
    private void setAbsoluteImageUrl(Room room) {
        // 构建基础URL，指向静态资源目录
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        
        // 设置图片的绝对URL路径
        String imageName;
        switch(room.getId().intValue()) {
            case 1: imageName = "activity_hall.jpeg"; break;
            case 2: imageName = "europe_room.jpeg"; break;
            case 3: imageName = "long_table.jpg"; break;
            case 4: imageName = "river_view.jpeg"; break;
            case 5: imageName = "wine_room.jpeg"; break;
            case 6: imageName = "side_view_tea.jpg"; break;
            case 7: imageName = "river_view_tea.jpg"; break;
            case 8: imageName = "central_dining.jpeg"; break;
            default: imageName = "default_room.jpg";
        }
        
        room.setImage(baseUrl + "/images/rooms/" + imageName);
    }
    
    // 初始化默认房间数据
    private List<Room> initializeRooms() {
        List<Room> defaultRooms = Arrays.asList(
            new Room("活动大厅", 40, "activity_hall.jpeg"),
            new Room("欧式房", 10, "europe_room.jpeg"),
            new Room("长桌室", 15, "long_table.jpg"),
            new Room("江景房", 5, "river_view.jpeg"),
            new Room("红酒房", 10, "wine_room.jpeg"),
            new Room("侧景茶室", 10, "side_view_tea.jpg"),
            new Room("江景茶室", 15, "river_view_tea.jpg"),
            new Room("中心餐厅", 30, "central_dining.jpeg")
        );
        
        return roomRepository.saveAll(defaultRooms);
    }
}