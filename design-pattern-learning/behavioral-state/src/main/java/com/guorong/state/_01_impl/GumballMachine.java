package com.guorong.state._01_impl;

/**
 * 糖果机
 */
class GumballMachine {


    /**
     * 当前状态(默认"已售罄"状态)
     */
    private State state = StateTypeEnum.SOLD_OUT_STATE.getSupplier().get();


    /**
     * 糖果机的糖果数量
     */
    private int count;

    public GumballMachine(int gumballNumbers) {
        // 糖果数量
        this.count = gumballNumbers;
        if (gumballNumbers > 0) {
            // 创建糖果机实例时，如果糖果数量大于0，设置糖果机的当前状态为"未投币"
            state = StateTypeEnum.NO_QUARTER_STATE.getSupplier().get();
        }
    }

    /**
     * 投币
     */
    void insertQuarter() {
        state.insertQuarter(this);
    }

    /**
     * 退币
     */
    void ejectQuarter() {
        state.ejectQuarter(this);
    }

    /**
     * 转动曲柄
     */
    void turnCrank() {
        // 转动曲柄
        state.turnCrank(this);
        // 发送糖果
        state.dispense(this);
    }

    /**
     * 设置当前状态
     *
     * @param currentState
     */
    public void changeState(State currentState) {
        this.state = currentState;
    }

    /**
     * 释放糖果
     */
    public void releaseBall() {
        if (count == 0) {
            return;
        }
        count--;
        System.out.println("糖果滚出...");
    }

    /**
     * 填充糖果
     *
     * @param count
     */
    public void fillGumball(int count) {
        this.count += count;
        state = StateTypeEnum.NO_QUARTER_STATE.getSupplier().get();
    }


    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "GumballMachine{" + "currentState=" + state.getClass().getSimpleName() + ", count=" + count + '}';
    }
}
