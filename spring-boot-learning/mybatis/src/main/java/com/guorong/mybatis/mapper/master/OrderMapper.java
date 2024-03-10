package com.guorong.mybatis.mapper.master;

import com.guorong.mybatis.entity.TypeHandlerDto;
import com.guorong.mybatis.vo.OrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {


    List<OrderVO> selectAllOrderVo();

}
