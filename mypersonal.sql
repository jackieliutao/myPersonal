/*
 Navicat Premium Data Transfer

 Source Server         : mysql5.7
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : mypersonal

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 19/12/2019 21:21:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT 0 COMMENT '员工数量',
  PRIMARY KEY (`dep_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '人事部', 0);
INSERT INTO `department` VALUES (2, '设计部', 2);
INSERT INTO `department` VALUES (3, '生产部', 3);
INSERT INTO `department` VALUES (4, '财务部', 0);
INSERT INTO `department` VALUES (5, '维修部', 3);
INSERT INTO `department` VALUES (7, '销售部', 1);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_age` int(11) NULL DEFAULT NULL,
  `emp_birthday` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_isBlack` tinyint(1) NULL DEFAULT NULL,
  `emp_img` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_id` int(11) NULL DEFAULT NULL,
  `emp_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `emp_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `professional` int(11) NULL DEFAULT NULL,
  `department` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `employee_ibfk_1`(`po_id`) USING BTREE,
  INDEX `professional`(`professional`) USING BTREE,
  INDEX `department`(`department`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`po_id`) REFERENCES `position` (`po_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`professional`) REFERENCES `professional` (`pro_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`department`) REFERENCES `department` (`dep_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (2, '张飞', '男', 21, '1998-11-25', 0, '2ad375d129644dfbafa2ff729f950a3b1575551877377.jpg', 13, '15865547854', '6513231', '12547622@163.com', 30, 3);
INSERT INTO `employee` VALUES (18, '张三', '女', 27, '1992-05-03', 0, '0952bf91d7af4dbfbb5e25bb182ad0211575551855668.jpg', 6, '18896799132', '98465132', '98465132@qq.com', 31, 7);
INSERT INTO `employee` VALUES (19, '关羽', '男', 24, '1995-11-21', 0, '7c015b2150f54643a0fe52eede5242f01575551906193.jpg', 16, '15589654784', '48426842132', '646132@163.com', 17, 5);
INSERT INTO `employee` VALUES (20, '周琦', '男', 24, '1995-02-03', 0, '9cf874fe1c1b4bbfba570d7b8e8e83f81575551926412.jpg', 11, '1568946565', '7984132321', '1568946565@qq.com', 13, 2);
INSERT INTO `employee` VALUES (21, '李楠', '男', 32, '1987-05-06', 0, '', 15, '1689846613', '7984132262', '6131@163.com', 19, 5);
INSERT INTO `employee` VALUES (32, '鲁班', '男', 11, '2008-12-16', 0, '04ccea9be7b9436ba914bf8a8a51d2ed1575593891569.jpg', 14, '179846513', '98465132', '179846513@qq.com', 30, 3);
INSERT INTO `employee` VALUES (37, '鲁智深', '男', 2, '2017-12-03', 0, '839fa0a476db44f48c6129b075bdc11b1575554044350.jpg', 15, '18852249654', '7984651', '7984651@qq.com', 17, 5);
INSERT INTO `employee` VALUES (38, '李四', '男', 0, '2019-12-01', 0, 'a2e256937c3c4b66817fbcffd6f8f6671575610287536.jpg', 11, '1978465984', '9846113', '9846113@qq.com', 13, 2);
INSERT INTO `employee` VALUES (39, '刘亦菲', '女', 5, '2014-12-15', 0, '8eb6f0f8ad4a4ea9a6ecc8b685e6958f1576051011386.jpg', 13, '1779845231', '98465132', '9846123@qq.com', 30, 3);

-- ----------------------------
-- Table structure for family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family`  (
  `fa_id` int(11) NOT NULL AUTO_INCREMENT,
  `fa_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fa_rel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属关系',
  `fa_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fa_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fa_group` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '党派',
  `fa_position` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `emp_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`fa_id`) USING BTREE,
  INDEX `emp_id`(`emp_id`) USING BTREE,
  CONSTRAINT `family_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family
-- ----------------------------
INSERT INTO `family` VALUES (1, '鲁豫', '母亲', '北京市朝阳区', '1984651', '中共党员', '主持人', 37);
INSERT INTO `family` VALUES (4, '汪峰', '父亲', '北京市', '169845613', '无', '歌手', 18);
INSERT INTO `family` VALUES (5, '范冰冰', '母亲', '泰国', '1687684561', '暂无', '戏精', 19);
INSERT INTO `family` VALUES (7, '蔡徐坤', '父亲', '江苏省连云港市', '1287986451', '中共党员', '篮球运动员', 21);
INSERT INTO `family` VALUES (9, '高晓松', '父亲', '北京市海淀区', '17798465132', '中共党员', '作词', 32);
INSERT INTO `family` VALUES (11, '李四父亲', '父亲', '南京市', '1984651398', '暂无', '歌手', 38);
INSERT INTO `family` VALUES (13, '张菲', '父亲', '上海市', '179846513', '无党派人士', '程序猿', 2);
INSERT INTO `family` VALUES (14, '周涛', '父亲', '河南省洛阳', '1697846451', '共青团员', '篮球运动员', 20);
INSERT INTO `family` VALUES (15, '刘一手', '父亲', '北京市', '1689465131', '无', '火锅店长', 39);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'admin', '123456', 1);
INSERT INTO `manager` VALUES (2, 'zs', '1234', 0);
INSERT INTO `manager` VALUES (5, 'ls', '123', 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentid` int(11) NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `spread` tinyint(1) NULL DEFAULT NULL,
  `href` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '&#xe653;', 0, '部门管理', 0, NULL);
INSERT INTO `menu` VALUES (2, '&#xe653;', 1, '部门列表', 0, '/department/departmentMenu');
INSERT INTO `menu` VALUES (3, '&#xe663;', 0, '职位管理', 0, NULL);
INSERT INTO `menu` VALUES (4, '&#xe61c;', 3, '职位列表', 0, '/position/positionMenu');
INSERT INTO `menu` VALUES (5, '&#xe61c;', 3, '职称列表', 0, '/professional/professionalMenu');
INSERT INTO `menu` VALUES (6, '&#xe770;', 0, '员工管理', 0, '');
INSERT INTO `menu` VALUES (7, '&#xe613;', 6, '员工列表', 0, '/employee/employeeMenu');
INSERT INTO `menu` VALUES (8, '&#xe61c;', 6, '工作经历', 0, '/workExperience/workExperienceMenu');
INSERT INTO `menu` VALUES (9, '&#xe61c;', 6, '奖惩详情', 0, '/reward/rewardMenu');
INSERT INTO `menu` VALUES (10, '&#xe61c;', 6, '学习记录', 0, '/study/studyMenu');
INSERT INTO `menu` VALUES (11, '&#xe61c;', 0, '管理员', 0, '');
INSERT INTO `menu` VALUES (12, '&#xe613;', 11, '管理员列表', 0, '/manage/manageMenu');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `po_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `po_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位编号',
  `po_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dep_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`po_id`) USING BTREE,
  UNIQUE INDEX `unique_po_num`(`po_num`) USING BTREE,
  INDEX `dep_id`(`dep_id`) USING BTREE,
  CONSTRAINT `position_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (4, 'c0011', '财务经理', 4);
INSERT INTO `position` VALUES (5, 'c110', '财务专员', 4);
INSERT INTO `position` VALUES (6, 'x110', '销售人员', 7);
INSERT INTO `position` VALUES (7, 'x001', '销售经理', 7);
INSERT INTO `position` VALUES (8, 's00011', '设计师', 2);
INSERT INTO `position` VALUES (11, 's1110', '设计助理', 2);
INSERT INTO `position` VALUES (12, 'sc110', '生产一线员工', 3);
INSERT INTO `position` VALUES (13, 'sc0001', '生产经理', 3);
INSERT INTO `position` VALUES (14, 'sc0002', '生产主管', 3);
INSERT INTO `position` VALUES (15, 'wx0011', '维修技术员', 5);
INSERT INTO `position` VALUES (16, 'wx0012', '维修助理', 5);
INSERT INTO `position` VALUES (18, 'wx0001', '维修经理', 5);

-- ----------------------------
-- Table structure for professional
-- ----------------------------
DROP TABLE IF EXISTS `professional`;
CREATE TABLE `professional`  (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pro_id`) USING BTREE,
  INDEX `department`(`department`) USING BTREE,
  CONSTRAINT `professional_ibfk_1` FOREIGN KEY (`department`) REFERENCES `department` (`dep_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of professional
-- ----------------------------
INSERT INTO `professional` VALUES (11, '初级设计师', 2);
INSERT INTO `professional` VALUES (12, '中级设计师', 2);
INSERT INTO `professional` VALUES (13, '高级设计师', 2);
INSERT INTO `professional` VALUES (14, '初级会计师', 4);
INSERT INTO `professional` VALUES (15, '中级会计师', 4);
INSERT INTO `professional` VALUES (16, '高级会计师', 4);
INSERT INTO `professional` VALUES (17, '初级维护员', 5);
INSERT INTO `professional` VALUES (18, '中级维护员', 5);
INSERT INTO `professional` VALUES (19, '高级维护经理', 5);
INSERT INTO `professional` VALUES (29, '初级生产员', 3);
INSERT INTO `professional` VALUES (30, '高级生产员', 3);
INSERT INTO `professional` VALUES (31, '初级销售业务员', 7);

-- ----------------------------
-- Table structure for reward
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward`  (
  `re_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `re_money` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employee` int(11) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `re_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`re_id`) USING BTREE,
  INDEX `employee`(`employee`) USING BTREE,
  CONSTRAINT `reward_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES (4, '上班玩手机', '-100', 18, 0, '2017-08-14');
INSERT INTO `reward` VALUES (5, '上班迟到', '-50', 20, 0, '2019-12-10');
INSERT INTO `reward` VALUES (6, '工作出色', '+100', 19, 1, '2019-12-11');
INSERT INTO `reward` VALUES (7, '办公室谈恋爱', '-100', 21, 0, '2018-12-18');

-- ----------------------------
-- Table structure for study
-- ----------------------------
DROP TABLE IF EXISTS `study`;
CREATE TABLE `study`  (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_content` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `s_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employee` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`) USING BTREE,
  INDEX `employee`(`employee`) USING BTREE,
  CONSTRAINT `study_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study
-- ----------------------------
INSERT INTO `study` VALUES (5, 'java', '北京传智播客', 2);
INSERT INTO `study` VALUES (6, '高并发', '杭州', 21);
INSERT INTO `study` VALUES (7, '计算机网络', '上海尚硅谷', 32);
INSERT INTO `study` VALUES (8, '软件工程', '北京云思', 37);

-- ----------------------------
-- Table structure for workexperience
-- ----------------------------
DROP TABLE IF EXISTS `workexperience`;
CREATE TABLE `workexperience`  (
  `wb_id` int(11) NOT NULL AUTO_INCREMENT,
  `wb_company` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wb_position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wb_start` date NULL DEFAULT NULL,
  `wb_end` date NULL DEFAULT NULL,
  `employee` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`wb_id`) USING BTREE,
  INDEX `employee`(`employee`) USING BTREE,
  CONSTRAINT `workexperience_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workexperience
-- ----------------------------
INSERT INTO `workexperience` VALUES (3, '阿里巴巴有限公司', '技术总监', '2015-12-13', '2019-01-07', 18);

-- ----------------------------
-- View structure for employeeinfo
-- ----------------------------
DROP VIEW IF EXISTS `employeeinfo`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `employeeinfo` AS select `e`.`emp_id` AS `emp_id`,`e`.`emp_name` AS `emp_name`,`d`.`dep_name` AS `dep_name`,`po`.`po_name` AS `po_name`,`p`.`pro_name` AS `pro_name` from (((`employee` `e` left join `professional` `p` on((`e`.`professional` = `p`.`pro_id`))) left join `position` `po` on((`po`.`po_id` = `e`.`po_id`))) left join `department` `d` on((`d`.`dep_id` = `po`.`dep_id`)));

-- ----------------------------
-- Procedure structure for employeeCount
-- ----------------------------
DROP PROCEDURE IF EXISTS `employeeCount`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `employeeCount`()
begin
	select d.dep_name,p.pro_name,GROUP_CONCAT(e.emp_name) as name,count(e.emp_id) as count from department as d
	left JOIN professional as p
	on d.dep_id = p.department
	left join employee as e
	on e.professional=p.pro_id
	GROUP BY p.pro_id;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table employee
-- ----------------------------
DROP TRIGGER IF EXISTS `count_insert`;
delimiter ;;
CREATE TRIGGER `count_insert` AFTER INSERT ON `employee` FOR EACH ROW begin
 update department set count = count +1 where dep_id = new.department;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table employee
-- ----------------------------
DROP TRIGGER IF EXISTS `count_update`;
delimiter ;;
CREATE TRIGGER `count_update` AFTER UPDATE ON `employee` FOR EACH ROW begin
	if(old.department != new.department) then
		update department set count = department.count+1 where dep_id = new.department;
		update department set count = department.count-1 where dep_id = old.department;
	end if;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table employee
-- ----------------------------
DROP TRIGGER IF EXISTS `count_delete`;
delimiter ;;
CREATE TRIGGER `count_delete` AFTER DELETE ON `employee` FOR EACH ROW begin
	update department set count =count-1 where dep_id=old.department;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
