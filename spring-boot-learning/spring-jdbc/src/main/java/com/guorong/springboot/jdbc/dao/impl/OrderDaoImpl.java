package com.guorong.springboot.jdbc.dao.impl;

import com.guorong.springboot.jdbc.dao.OrderDao;
import com.guorong.springboot.jdbc.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author guorong
 * @date 2020-05-18
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long insertOrderInfo(Order order) {
        // 设置创建时间
        order.setCreatedTime(new Date());
        // sql
        String sql = "insert into s04_order(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, created_time) " +
                "values(:name, :street, :city, :state, :zip, :ccNumber, :ccExpiration, :ccCVV, :createdTime)";
        // sql参数
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(order);

        /*
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name", order.getName());
        sqlParameterSource.addValue("street", order.getStreet());
        sqlParameterSource.addValue("city", order.getCity());
        sqlParameterSource.addValue("state", order.getState());
        sqlParameterSource.addValue("zip", order.getZip());
        sqlParameterSource.addValue("ccNumber", order.getCcNumber());
        sqlParameterSource.addValue("ccExpiration", order.getCcExpiration());
        sqlParameterSource.addValue("ccCVV", order.getCcCVV());
        sqlParameterSource.addValue("createdTime", order.getCreatedTime(), Types.TIMESTAMP);
        */

        // KeyHolder用于数据库自动生成的主键
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // 执行新增
        jdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        // 返回主键
        return keyHolder.getKey().longValue();
    }


    @Override
    public void insertTacosToOrder(Long orderId, List<Long> tacoIdList) {
        // sql
        String sql = "insert into s04_taco_order(order_id,taco_id) values(:orderId,:tacoId)";
        // sql参数
        MapSqlParameterSource[] params = tacoIdList
                .stream()
                .map(tacoId -> {
                    return new MapSqlParameterSource("orderId", orderId).addValue("tacoId", tacoId);
                })
                .toArray(MapSqlParameterSource[]::new);
        // 执行批量插入
        jdbcTemplate.batchUpdate(sql, params);

    }


}
