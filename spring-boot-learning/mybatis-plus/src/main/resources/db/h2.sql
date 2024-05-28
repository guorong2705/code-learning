DROP TABLE IF EXISTS t_student;
CREATE TABLE t_student
(
    id          BIGINT primary key not null auto_increment,
    version     int          default null,
    name        VARCHAR(10)  DEFAULT NULL,
    age         INT          DEFAULT NULL,
    email       VARCHAR(30)  DEFAULT NULL,
    class_info  varchar(500) default null,
    delete_flag int          default null,
    create_time datetime     default null,
    update_time datetime     default null
);

-- 动态表名
drop table if exists t_dynamic_table_2023;
create table t_dynamic_table_2023
(
    id   bigint primary key not null auto_increment,
    name varchar(255)       not null
);

-- spring事务传播测试
DROP TABLE IF EXISTS propagation_entity;
create table propagation_entity
(
    id BIGINT primary key not null auto_increment,
    name        VARCHAR(10)  DEFAULT NULL
);