package com.guorong.mybatis.mapper.master;

import com.guorong.mybatis.entity.master.Order;
import com.guorong.mybatis.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderMapper {

    List<OrderVO> selectAllOrderVo();

    @Select("select * from t_order where order_no=#{orderNo}")
    Order selectByOrderNo(@Param("orderNo") String orderNo);

}
