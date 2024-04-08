-- 用户表
create table t_user
(
    user_id     bigint      not null auto_increment comment '用户主键',
    username    varchar(20) not null comment '用户名',
    password    varchar(50) not null comment '密码',
    nickname    varchar(50) null default null comment '昵称',
    age         int         null default null comment '年龄',
    create_time timestamp   not null default current_timestamp comment '创建时间',
    update_time timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (user_id)
);


-- 订单表
create table t_order
(
    id       bigint       not null auto_increment comment '主键',
    order_no varchar(100) not null comment '订单号',
    primary key (id)
);

-- 订单项
create table t_order_item
(
    id       bigint       not null auto_increment comment '主键',
    order_no varchar(64) not null comment '订单号',
    sku_code varchar(64) not null comment '商品编号',
    sku_name varchar(64) not null comment '商品名称',
    primary key (id)
);

-- 类型处理器
create table type_handler_table
(
    id        bigint not null auto_increment comment '主键',
    name      varchar(100) default null comment '名称',
    data_json varchar(300) default null comment '存储json数据'
);