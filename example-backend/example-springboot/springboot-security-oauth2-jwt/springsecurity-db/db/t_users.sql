DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '(1-正常,2-冻结)',
  `locked` tinyint(1) NULL DEFAULT NULL COMMENT '(0-正常,1-已锁定)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;