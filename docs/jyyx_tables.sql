--图片表
CREATE TABLE `jy_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `pic_length` int(11) DEFAULT '0' COMMENT '图片大小',
  `pic_code` varchar(64) NOT NULL DEFAULT '' COMMENT '图片类型code',
  `refer_id` int(11) DEFAULT '0' COMMENT '关联资源ID',
  `order_code` int(11) DEFAULT '0' COMMENT '排序号（越小越前）',
  `pic_path` varchar(256) NOT NULL DEFAULT '' COMMENT '图片路径',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';

--产品表
CREATE TABLE `jy_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `product_name` varchar(256) NOT NULL DEFAULT '' COMMENT '产品名字',
  `product_desc` text NULL COMMENT '产品详情',
  `product_link` varchar(128) NULL DEFAULT '' COMMENT '产品链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';

--产品分类
CREATE TABLE `jy_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `category_name` varchar(128) NOT NULL DEFAULT '' COMMENT '分类名字',
  `order_code` int(11) NULL DEFAULT '0' COMMENT '排序号（越小越前）',
  `parent_id` int(11) NULL DEFAULT '0' COMMENT '父分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品分类表';


--产品分类关系表
CREATE TABLE `jy_product_category_relation` (
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `order_code` int(11) NULL DEFAULT '0' COMMENT '排序号（越小越前）',
  PRIMARY KEY (`product_id`, `category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品-分类表';

--家装案例表
CREATE TABLE `jy_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `case_name` varchar(128) NOT NULL DEFAULT '' COMMENT '案例名称',
  `case_desc` varchar(512) NULL DEFAULT '' COMMENT '案例描述',
  `case_price` double NULL DEFAULT '0.00' COMMENT '案例价格',
  `case_tips` varchar(128) null default '' comment '案例主题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='家装案例表';

--案例分类表
CREATE TABLE `jy_case_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `category_name` varchar(128) NOT NULL DEFAULT '' COMMENT '分类名字',
  `order_code` int(11) NULL DEFAULT '0' COMMENT '排序号（越小越前）',
  `parent_id` int(11) NULL DEFAULT '0' COMMENT '父分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案例分类表';


--案例分类关系表
CREATE TABLE `jy_case_category_relation` (
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `case_id` int(11) NOT NULL COMMENT '案例ID',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `order_code` int(11) NULL DEFAULT '0' COMMENT '排序号（越小越前）',
  PRIMARY KEY (`case_id`, `category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案例-分类表';

--资讯分类表
CREATE TABLE `jy_msg_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `category_name` varchar(128) not null default '' comment '分类名字',
  `order_code` int(11) NULL DEFAULT '0' COMMENT '排序号（越小越前）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯分类表';


--资讯表
CREATE TABLE `jy_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `msg_title` varchar(256) not null default '' comment '资讯标题',
  `msg_content` text not null comment '资讯内容',
  `order_code` int(11) NULL DEFAULT '0' COMMENT '排序号（越小越前）',
  `category_id` int(11) not null comment '资讯分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯表';

--用户表
CREATE TABLE `jy_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_name` varchar(256) not null default '' comment '用户名',
  `login_name` varchar(128) not null comment '登录账号',
  `login_pwd` varchar(128) not null comment '登录密码',
  `user_phone` varchar(32) null comment '用户手机号',
  `user_email` varchar(256) null comment '用户邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
