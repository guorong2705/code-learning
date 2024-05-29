package com.guorong.mp.tx_propagation;


import com.guorong.mp.entity.PropagationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class NestedManager {

    private final PropagationService propagationService;


    // 场景一：在外围方法未开启事务的情况下 Propagation.NESTED 和 Propagation.REQUIRED 作用相同，
    // 修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。

    /**
     * 结果：“张三”、“李四”均插入。
     *
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    public void no_transaction_exception_nested_nested(){
        propagationService.addNested(new PropagationEntity(1L, "张三"));
        propagationService.addNested(new PropagationEntity(2L, "李四"));
        throw new RuntimeException("张三和李四成功");
    }

    /**
     * 结果：“张三”插入，“李四”未插入。
     *
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,
     * 所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    public void no_transaction_nested_nested_exception(){
        propagationService.addNested(new PropagationEntity(1L, "张三"));

        propagationService.addNestedException(new PropagationEntity(2L, "李四"));
    }



    // --------------------------------------------------------------------------------
    // 场景二：以上试验结果我们证明在外围方法开启事务的情况下 Propagation.NESTED 修饰的内部方法属于外部事务的子事务，
    // 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务

    /**
     * 结果：“张三”、“李四”均未插入。
     *
     * 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_nested_nested(){
        propagationService.addNested(new PropagationEntity(1L, "张三"));
        propagationService.addNested(new PropagationEntity(2L, "李四"));
        throw new RuntimeException("张三，李四插入失败");
    }


    /**
     * 结果：“张三”、“李四”均未插入。
     *
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception(){
        propagationService.addNested(new PropagationEntity(1L, "张三"));
        propagationService.addNestedException(new PropagationEntity(2L, "李四"));
    }


    /**
     * 结果：“张三”插入、“李四”未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，插入“李四”内部方法抛出异常，异常被捕获，
     * 外围事务感知不到，单独对子事务回滚。外围事务正常提交。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception_try(){
        propagationService.addNested(new PropagationEntity(1L, "张三"));
        try {
            propagationService.addNestedException(new PropagationEntity(2L, "李四"));
        } catch (Exception e) {
            System.out.println("方法回滚:李四");
        }
    }


}
