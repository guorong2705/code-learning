package com.guorong.state._01_impl;

import lombok.Getter;

import java.util.function.Supplier;

// 状态枚举
enum StateTypeEnum {
    /**未投币"*/
    NO_QUARTER_STATE(NoQuarterState::getInstance),
    /**已投币*/
    HAS_QUARTER_STATE(HasQuarterState::getInstance),
    /**已发糖状态*/
    SOLD_STATE(SoldState::getInstance),
    /**已售罄状态*/
    SOLD_OUT_STATE(SoldOutState::getInstance),
    /**赢家状态*/
    WINNER_STATE(WinnerState::getInstance),
    ;
    @Getter
    private Supplier<State> supplier;

    StateTypeEnum(Supplier<State> supplier) {
        this.supplier = supplier;
    }

}
