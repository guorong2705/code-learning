package com.guorong.mybatis.mapper.master;

import com.guorong.mybatis.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test() {
        List<OrderVO> orderVOList = orderMapper.selectAllOrderVo();
        orderVOList.forEach(orderVO -> log.info("订单数据--->>> {}", orderVO));
    }
}
