package com.guorong.mp.tx_propagation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guorong.mp.entity.PropagationEntity;
import com.guorong.mp.mapper.PropagationEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropagationService extends ServiceImpl<PropagationEntityMapper, PropagationEntity> {

    @Autowired
    private PropagationEntityMapper propagationEntityMapper;

    @Transactional(rollbackFor = Exception.class, propagation = org.springframework.transaction.annotation.Propagation.REQUIRED)
    public void addRequired(PropagationEntity entity) {
        propagationEntityMapper.insert(entity);
    }

    @Transactional(rollbackFor = Exception.class, propagation = org.springframework.transaction.annotation.Propagation.REQUIRED)
    public void addRequiredException(PropagationEntity entity) {
        propagationEntityMapper.insert(entity);
        throw new RuntimeException("addRequireException 错误");
    }


    @Transactional(rollbackFor = Exception.class, propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
    public void addRequireNew(PropagationEntity entity) {
        propagationEntityMapper.insert(entity);
    }

    @Transactional(rollbackFor = Exception.class, propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
    public void addRequireNewException(PropagationEntity entity) {
        propagationEntityMapper.insert(entity);
        throw new RuntimeException("addRequireNewException 错误");
    }

    @Transactional(rollbackFor = Exception.class, propagation = org.springframework.transaction.annotation.Propagation.NESTED)
    public void addNested(PropagationEntity entity) {
        propagationEntityMapper.insert(entity);
    }

    @Transactional(rollbackFor = Exception.class, propagation = org.springframework.transaction.annotation.Propagation.NESTED)
    public void addNestedException(PropagationEntity entity) {
        propagationEntityMapper.insert(entity);
        throw new RuntimeException("addNestedException 错误");
    }

}
