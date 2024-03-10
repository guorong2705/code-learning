package com.guorong.mybatis.mapper;

import com.guorong.mybatis.mapper.master.OrderMapper;
import com.guorong.mybatis.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
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
