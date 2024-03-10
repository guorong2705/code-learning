-- 学生表
drop table if exists `student`;
create table if not exists `student`
(
    `id`          int         not null auto_increment comment '学生编号',
    `name`        varchar(10) not null comment '学生姓名',
    `age`         int       default null comment '学生年龄',
    `create_time` timestamp default current_timestamp comment '创建时间',
    `update_time` timestamp default current_timestamp on update CURRENT_TIMESTAMP comment '更新时间',
    primary key (`id`)
) comment ='学生表';

