-- 公司表
create table t_company
(
    company_id   bigint       not null auto_increment comment '主键',
    company_name varchar(100) not null comment '公司名称',
    address      varchar(100) default null comment '公司地址'
);





