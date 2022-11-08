use mysql;
DROP database if EXISTS `table_test`;
CREATE database `table_test` default character set utf8 collate utf8_general_ci;
use `table_test`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `table`;
CREATE TABLE `table`  (
                         `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `birthday` date NULL DEFAULT NULL,
                         `salary` double NULL DEFAULT NULL,
                         `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `remark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment 'Remark',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;