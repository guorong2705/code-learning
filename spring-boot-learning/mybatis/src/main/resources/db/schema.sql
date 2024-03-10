-- 用户表
drop table if exists `t_user`;
create table `t_user`
(
    `user_id`     bigint      not null auto_increment comment '用户主键',
    `username`    varchar(20) not null comment '用户名',
    `password`    varchar(50) not null comment '密码',
    `nickname`    varchar(50) null default null comment '昵称',
    `age`         int(3) null default null comment '年龄',
    `create_time` timestamp null default current_timestamp comment '创建时间',
    `update_time` timestamp null default null on update current_timestamp comment '更新时间',
    primary key (`user_id`)
);

-- 公司表
drop table if exists `t_company`;
create table t_company
(
    company_id   bigint       not null auto_increment comment '主键',
    company_name varchar(100) not null comment '公司名称',
    address      varchar(100) default null comment '公司地址'
);


-- 公司表
drop table if exists `type_handler_table`;
create table type_handler_table
(
    id        bigint not null auto_increment comment '主键',
    name      varchar(100) default null comment '名称',
    data_json varchar(300) default null comment '存储json数据'
);


-- 订单表
drop table if exists `t_order`;
create table t_order
(
    id       bigint       not null auto_increment comment '主键',
    order_no varchar(100) not null comment '订单号',
    primary key (id)
);
-- 订单项
drop table if exists `t_order_item`;
create table t_order_item
(
    id       bigint       not null auto_increment comment '主键',
    order_no varchar(64) not null comment '订单号',
    sku_code varchar(64) not null comment '商品编号',
    sku_name varchar(64) not null comment '商品名称',
    primary key (id)
);