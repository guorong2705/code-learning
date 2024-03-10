-- 订单数据
insert into t_order(id,order_no) values (1,'1001');
insert into t_order(id,order_no) values (2,'1002');
insert into t_order(id,order_no) values (3,'1003');
-- 订单项数据
insert into t_order_item(id,order_no,sku_code,sku_name) values (1, '1001','s001', '苹果手机');
insert into t_order_item(id,order_no,sku_code,sku_name) values (2, '1001','s002', '苹果平板');
insert into t_order_item(id,order_no,sku_code,sku_name) values (3, '1001','s003', '苹果电脑');