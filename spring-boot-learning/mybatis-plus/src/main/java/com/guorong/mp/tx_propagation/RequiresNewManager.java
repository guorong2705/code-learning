package com.guorong.mp.tx_propagation;

import com.guorong.mp.entity.PropagationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * PROPAGATION_REQUIRES_NEW
 * 新建事务，如果当前存在事务，把当前事务挂起。
 **/
@RequiredArgsConstructor
@Component
public class RequiresNewManager {

    private final PropagationService propagationService;

    // 场景一：通过这两个方法我们证明了在外围方法未开启事务的情况下
    // Propagation.REQUIRES_NEW 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。

    /**
     * 结果：“张三”插入，“李四”插入。
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,外围方法抛出异常不会影响内部方法。
     */
    public void no_transaction_exception_requiresNew_requiresNew(){
        propagationService.addRequireNew(new PropagationEntity(1L, "张三"));
        propagationService.addRequireNew(new PropagationEntity(2L, "李四"));
        throw new RuntimeException("“张三”插入，“李四”插入。");
    }


    /**
     * 结果：“张三”插入，“李四”未插入
     * 外围方法没有开启事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，插入“李四”方法抛出异常回滚，其他事务不受影响。
     */
    public void no_transaction_requiresNew_requiresNew_exception(){
        propagationService.addRequireNew(new PropagationEntity(1L, "张三"));
        propagationService.addRequireNewException(new PropagationEntity(2L, "李四"));
    }



    // 场景二：在外围方法开启事务的情况下 Propagation.REQUIRES_NEW 修饰的内部方法依然会单独开启独立事务，
    // 且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。

    /**
     * 结果：“张三”未插入，“李四”插入，“王五”插入。
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中，
     * 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三”的方法回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew(){
        propagationService.addRequired(new PropagationEntity(1L, "张三"));

        propagationService.addRequireNew(new PropagationEntity(2L, "李四"));

        propagationService.addRequireNew(new PropagationEntity(3L, "王五"));

        throw new RuntimeException("“张三”未插入，“李四”插入，“王五”插入。");
    }

    /**
     * 结果：“张三”未插入，“李四”插入，“王五”未插入。
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入 “王五”方法的事务被回滚，异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入“张三”方法也被回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception(){

        propagationService.addRequired(new PropagationEntity(1L, "张三"));

        propagationService.addRequireNew(new PropagationEntity(2L, "李四"));

        propagationService.addRequireNewException(new PropagationEntity(3L, "王五"));
    }


    /**
     * 结果：“张三”插入，“李四”插入，“王五”未插入。
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入“王五”方法的事务被回滚，异常被catch不会被外围方法感知，外围方法事务不回滚，故插入“张三”方法插入成功。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try(){
        propagationService.addRequired(new PropagationEntity(1L, "张三"));

        propagationService.addRequireNew(new PropagationEntity(2L, "李四"));

        try {
            propagationService.addRequireNewException(new PropagationEntity(3L, "王五"));
        } catch (Exception e) {
            System.out.println("回滚:王五");
        }
    }














}
