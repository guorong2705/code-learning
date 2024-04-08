package com.guorong.mybatis.mapper.master;

import com.guorong.mybatis.vo.OrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderMapper {

    List<OrderVO> selectAllOrderVo();

}
