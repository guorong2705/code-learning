create table scm_dev_1.t_student
(
    id          bigint auto_increment
        primary key,
    version     int          null,
    delete_flag int          null,
    create_time datetime     null,
    update_time datetime     null,
    name        varchar(20)  null,
    age         int          null,
    email       varchar(300) null,
    class_info  varchar(300) null
);

