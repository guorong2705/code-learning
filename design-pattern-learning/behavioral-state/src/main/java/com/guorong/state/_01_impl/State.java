package com.guorong.state._01_impl;

import java.util.Random;

/**
 * 状态接口 (将全部实现类作为单例实现)
 */
interface State {

    /**
     * 投币
     */
    void insertQuarter(GumballMachine gumballMachine);

    /**
     * 退币
     */
    void ejectQuarter(GumballMachine gumballMachine);

    /**
     * 转动曲柄
     */
    void turnCrank(GumballMachine gumballMachine);

    /**
     * 发放糖果
     */
    void dispense(GumballMachine gumballMachine);
}

/**
 * "未投币" 状态
 */
class NoQuarterState implements State {

    private static NoQuarterState instance = new NoQuarterState();

    private NoQuarterState() {}

    /**
     * 获取单例
     * @return
     */
    public static NoQuarterState getInstance() {
        return instance;
    }

    @Override
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("投入了一个硬币");
        // 修改糖果机状态为"已投币"
        gumballMachine.changeState(StateTypeEnum.HAS_QUARTER_STATE.getSupplier().get());
    }

    @Override
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("对不起，你还没有投币，无法退币");
    }

    @Override
    public void turnCrank(GumballMachine gumballMachine) {
        System.out.println("对不起，你还没有投币");
    }

    @Override
    public void dispense(GumballMachine gumballMachine) {
        System.out.println("对不起，你还没有投币");
    }
}

/**
 * 已投币状态
 */
class HasQuarterState implements State {

    private static HasQuarterState instance = new HasQuarterState();

    private HasQuarterState() {}

    /**
     * 获取单例
     * @return
     */
    public static HasQuarterState getInstance() {
        return instance;
    }

    @Override
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("对不起，不支持该操作，已存在一个硬币了");
    }

    @Override
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("退硬币成功");
        // 修改糖果机状态为 "未投币"
        gumballMachine.changeState(StateTypeEnum.NO_QUARTER_STATE.getSupplier().get());
    }

    @Override
    public void turnCrank(GumballMachine gumballMachine) {
        System.out.println("转动曲柄成功...");
        // 随机决定赢家模式
        int rand = new Random().nextInt(10);
        // 赢家模式
        if (rand == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.changeState(StateTypeEnum.WINNER_STATE.getSupplier().get());
        }
        // 转换为 "已售出"状态
        else {
            gumballMachine.changeState(StateTypeEnum.SOLD_STATE.getSupplier().get());
        }
    }

    @Override
    public void dispense(GumballMachine gumballMachine) {
        System.out.println("对不起，不支持该操作");
    }
}

/**
 * 已售罄状态
 */
class SoldOutState implements State {

    private static SoldOutState instance = new SoldOutState();

    private SoldOutState() {}

    /**
     * 获取单例
     * @return
     */
    public static SoldOutState getInstance() {
        return instance;
    }

    @Override
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("对不起，糖果已经售罄了，不支持投币");
    }

    @Override
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("对不起，糖果已经售罄了，不支持退币");
    }

    @Override
    public void turnCrank(GumballMachine gumballMachine) {
        System.out.println("对不起，糖果已经售罄了，转动曲柄不会弹出糖果");
    }

    @Override
    public void dispense(GumballMachine gumballMachine) {
        System.out.println("对不起，糖果已经售罄了，不支持发放糖果");
    }
}

/**
 * 已发糖状态
 */
class SoldState implements State {

    private static SoldState instance = new SoldState();

    private SoldState() {}

    /**
     * 获取单例
     * @return
     */
    public static SoldState getInstance() {
        return instance;
    }

    @Override
    public void dispense(GumballMachine gumballMachine) {
        // 调用糖果机的释放糖果方法
        gumballMachine.releaseBall();
        // 修改为未投币状态
        if (gumballMachine.getCount() > 0) {
            gumballMachine.changeState(StateTypeEnum.NO_QUARTER_STATE.getSupplier().get());
        }
        // 修改为已售罄状态
        else {
            gumballMachine.changeState(StateTypeEnum.SOLD_OUT_STATE.getSupplier().get());
            System.out.println("糖果机的糖果已售罄");
        }
    }

    @Override
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("对不起，当前状态不支持，投币");
    }

    @Override
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("对不起，已经转动了曲柄，不支持退币");
    }

    @Override
    public void turnCrank(GumballMachine gumballMachine) {
        System.out.println("对不起，不能连续转动曲柄两次");
    }
}

/**
 * 赢家状态
 */
class WinnerState implements State {

    private static WinnerState instance = new WinnerState();

    private WinnerState() {}

    public static WinnerState getInstance() {
        return instance;
    }

    @Override
    public void dispense(GumballMachine gumballMachine) {
        System.out.println("你是赢家，可以得到两个糖果");
        // 发放糖果
        gumballMachine.releaseBall();
        // 糖果已售罄
        if (gumballMachine.getCount() == 0) {
            gumballMachine.changeState(StateTypeEnum.SOLD_OUT_STATE.getSupplier().get());
            System.out.println("糖果已经售罄");
            return;
        }
        // 发放第二个糖果
        gumballMachine.releaseBall();
        // 第二次发放糖果后，根据糖果机中的糖果，设置糖果机的状态
        if (gumballMachine.getCount() == 0) {
            gumballMachine.changeState(StateTypeEnum.SOLD_OUT_STATE.getSupplier().get());
            System.out.println("糖果已经售罄");
            return;
        }
        // 还剩糖果设置糖果状态为 未投币
        gumballMachine.changeState(StateTypeEnum.NO_QUARTER_STATE.getSupplier().get());
    }

    @Override
    public void insertQuarter(GumballMachine gumballMachine) {
        System.out.println("不支持该操作");
    }

    @Override
    public void ejectQuarter(GumballMachine gumballMachine) {
        System.out.println("不支持该操作");
    }

    @Override
    public void turnCrank(GumballMachine gumballMachine) {
        System.out.println("不支持该操作");
    }
}