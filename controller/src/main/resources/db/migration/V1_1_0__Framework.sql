CREATE TABLE `sys_user` (
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长流水号',
  `user_code` varchar(20) NOT NULL COMMENT '用户名',
  `user_password` varchar(64) DEFAULT '' COMMENT '密码',
  `user_name` varchar(20) DEFAULT '' COMMENT '用户姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(50) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_person` varchar(50) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `uq_sys_user_1` (`user_code`)
);

CREATE TABLE `sys_resource` (
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长流水号',
  `resource_code` varchar(20) NOT NULL COMMENT '资源代码',
  `resource_name` varchar(50) DEFAULT '' COMMENT '资源名',
  `resource_type` varchar(4) DEFAULT '' COMMENT '0-应用模块，1-实体资源',
  `resource_content` varchar(100) DEFAULT '' COMMENT '资源内容',
  `resource_desc` varchar(50) DEFAULT '' COMMENT '资源描述',
  `parent_resource_code` varchar(20) DEFAULT '' COMMENT '父资源Id',
  `app_type` varchar(4) DEFAULT '' COMMENT '应用类型',
  `resource_icon` varchar(50) DEFAULT '' COMMENT '资源图标',
  `app_version` varchar(20) DEFAULT '' COMMENT '应用版本',
  `ad` varchar(20) DEFAULT '' COMMENT '缩写',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(50) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_person` varchar(50) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `uq_sys_resource_1` (`resource_code`)
);

CREATE TABLE `sys_role_resource` (
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长流水号',
  `resource_code` varchar(20) NOT NULL COMMENT '资源ID',
  `role_code` varchar(20) NOT NULL COMMENT '角色ID',
  `relation` varchar(50) NOT NULL DEFAULT '' COMMENT '资源授权关系(Grant-授权访问, Deny-禁止访问, ReadOnly-只读)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(50) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_person` varchar(50) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `u_sys_role_resource_mapping_1` (`resource_code`,`role_code`)
);

CREATE TABLE `sys_role` (
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长流水号',
  `role_code` varchar(20) NOT NULL COMMENT '角色编码',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(50) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_person` varchar(50) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `code_key` (`role_code`)
);

CREATE TABLE `sys_user_role` (
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长流水号',
  `user_code` varchar(20) NOT NULL COMMENT '用户ID',
  `role_code` varchar(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_person` varchar(50) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_person` varchar(50) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `user_role_key` (`user_code`,`role_code`)
);

CREATE TABLE `sys_token` (
  `seq_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增长流水号',
  `user_code` varchar(20) NOT NULL COMMENT '用户名',
  `token_content` varchar(100) NOT NULL COMMENT '令牌内容',
  `expiredate` datetime DEFAULT NULL COMMENT '过期时间',
  `isvalid` int(11) DEFAULT NULL COMMENT '令牌是否有效 0为无效，1为有效',
  `ipaddress` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `ismobile` int(11) DEFAULT NULL COMMENT '是否为移动设备 0为PC，1位移动设备',
  PRIMARY KEY (`seq_id`),
  UNIQUE KEY `u_sys_token_1` (`token_content`)
);

