-- 检查表是否为空，只有在表为空时才插入数据
INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-28', 'morning', 1, '一支行', '08:30-12:00', '张经理', '13800000001', 30, '团队建设会议', '需要投影设备'
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-28', 'morning', 2, '二支行', '09:00-11:30', '李经理', '13800000002', 8, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-28', 'morning', 5, '东山支行', '10:00-12:00', '王经理', '13800000003', 6, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-28', 'afternoon', 3, '荔湾支行', '14:00-17:00', '赵经理', '13800000004', 12, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-29', 'morning', 4, '天河支行', '09:00-11:00', '钱经理', '13800000005', 4, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-29', 'afternoon', 6, '白云支行', '13:30-16:30', '孙经理', '13800000006', 8, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-30', 'morning', 7, '海珠支行', '08:30-11:30', '周经理', '13800000007', 10, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-30', 'afternoon', 8, '越秀支行', '14:00-16:00', '吴经理', '13800000008', 25, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 1, '一支行', '08:30-12:00', '张经理', '13800000001', 30, '季度工作会议', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 2, '二支行', '09:00-11:30', '李经理', '13800000002', 8, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 3, '三支行', '10:00-12:00', '王经理', '13800000003', 12, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 4, '荔湾支行', '09:00-11:00', '赵经理', '13800000004', 4, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 5, '天河支行', '08:30-12:00', '钱经理', '13800000005', 9, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 6, '白云支行', '09:30-11:30', '孙经理', '13800000006', 8, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 7, '海珠支行', '10:00-12:00', '周经理', '13800000007', 10, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);

INSERT INTO booking (date, period, room_id, branch, time, contact, phone, attendees, theme, notes)
SELECT '2025-07-31', 'morning', 8, '越秀支行', '08:30-11:30', '吴经理', '13800000008', 25, '', ''
WHERE NOT EXISTS (SELECT 1 FROM booking LIMIT 1);