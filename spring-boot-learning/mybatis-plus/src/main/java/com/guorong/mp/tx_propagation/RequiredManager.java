package com.guorong.mp.tx_propagation;

import com.guorong.mp.entity.PropagationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * PROPAGATION_REQUIRED
 * 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
 */
@RequiredArgsConstructor
@Component
public class RequiredManager {

    private final PropagationService propagationService;


    // 场景一：通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，
    // 且开启的事务相互独立，互不干扰。

    /**
     * 结果：张三”、“李四”均插入。
     * <p>
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    public void no_transaction_exception_required_required() {
        propagationService.addRequired(new PropagationEntity(1L, "张三"));
        propagationService.addRequired(new PropagationEntity(2L, "李四"));
        throw new RuntimeException("结果：张三”、“李四”均插入。");
    }


    /**
     * 结果：“张三”插入，“李四”未插入。
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    public void no_transaction_required_required_exception() {
        propagationService.addRequired(new PropagationEntity(1L, "张三"));
        propagationService.addRequiredException(new PropagationEntity(2L, "李四"));
    }




    // 场景二：以下试验结果证明在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，
    // 所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。


    /**
     * 结果：“张三”、“李四”均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        propagationService.addRequired(new PropagationEntity(1L, "张三"));
        propagationService.addRequired(new PropagationEntity(2L, "李四"));
        throw new RuntimeException("“张三”、“李四”均未插入。");
    }


    /**
     * 结果：“张三”、“李四”均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        propagationService.addRequired(new PropagationEntity(1L, "张三"));
        propagationService.addRequiredException(new PropagationEntity(2L, "李四"));
    }


    /**
     * 结果：“张三”、“李四”均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚。
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception_try(){
        propagationService.addRequired(new PropagationEntity(1L, "张三"));
        try {
            propagationService.addRequiredException(new PropagationEntity(2L, "李四"));
        } catch (Exception e) {

        }
    }

}
