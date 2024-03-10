-- 玉米饼成分表
drop table if exists `taco_ingredient`;
CREATE TABLE `taco_ingredient`
(
    `id`   VARCHAR(100) NOT NULL COMMENT '玉米饼成分主键',
    `name` VARCHAR(100) NOT NULL COMMENT '成分名称',
    `type` VARCHAR(100) NOT NULL COMMENT '成分类型',
    primary key (`id`)
);
alter table `taco_ingredient`
    comment '玉米饼成分表';


-- 玉米饼表
drop table if exists `taco`;
CREATE TABLE `taco`
(
    `id`           BIGINT AUTO_INCREMENT comment '玉米饼主键',
    `name`         VARCHAR(100) NOT NULL comment '玉米饼的名称',
    `created_time` TIMESTAMP    NOT NULL default current_timestamp COMMENT '玉米饼的创建时间',
    primary key (`id`)
);
alter table `taco`
    comment '玉米饼表';


-- 玉米饼成分关系表
drop table if exists `taco_ingredient_relation`;
CREATE TABLE `taco_ingredient_relation`
(
    `taco_id`       BIGINT       NOT NULL COMMENT '玉米饼id',
    `ingredient_id` VARCHAR(100) NOT NULL COMMENT '玉米饼成分id'
);
alter table `taco_ingredient_relation`
    comment '玉米饼和成分关系表';


-- 玉米饼订单表
drop table if exists `taco_order`;
CREATE TABLE `taco_order`
(
    `id`              BIGINT      NOT NULL AUTO_INCREMENT comment '订单id',
    `delivery_name`   VARCHAR(50) NOT NULL COMMENT '交货人',
    `delivery_street` VARCHAR(50) NOT NULL COMMENT '交货街道',
    `delivery_city`   VARCHAR(50) NOT NULL COMMENT '交货城市',
    `delivery_state`  VARCHAR(50) NOT NULL COMMENT '交货州：美国的州: 例如：纽约州',
    `delivery_zip`    VARCHAR(10) NOT NULL COMMENT '邮编',
    `cc_number`       VARCHAR(50) NOT NULL COMMENT '信用卡编号',
    `cc_expiration`   VARCHAR(20) NOT NULL COMMENT '信用卡有效期',
    `cc_cvv`          VARCHAR(3)  NOT NULL COMMENT '信用卡安全码',
    `created_time`    TIMESTAMP   NOT NULL default current_timestamp COMMENT '订单创建时间',
    primary key (`id`)
);
alter table `taco_order`
    comment '玉米饼订单表';


-- 玉米饼和订单的关系表
drop table if exists `taco_order_relation`;
CREATE TABLE `taco_order_relation`
(
    `order_id` BIGINT NOT NULL COMMENT '订单id',
    `taco_id`  BIGINT NOT NULL COMMENT '玉米饼id'
);
alter table `taco_order_relation`
    comment '玉米饼和订单关联表';



-- 插入数据---------------------------------------------------------

-- 删除表中数据
truncate table `taco_ingredient`;
-- 插入数据
INSERT INTO taco_ingredient (id, name, type)
VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO taco_ingredient (id, name, type)
VALUES ('SRCR', 'Sour Cream', 'SAUCE');
