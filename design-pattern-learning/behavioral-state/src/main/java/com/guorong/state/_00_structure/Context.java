package com.guorong.state._00_structure;

// 环境类
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle();
    }

}
