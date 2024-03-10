package com.guorong.command._01_impl;

/**
 * 命令接口：所有的命令对象，都实现该接口
 */
interface Command {
    // 执行
    void execute();
    // 撤销
    void undo();
}

/**
 * 命令对象：关灯命令
 */
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

/**
 * 命令对象：开灯命令
 */
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

/**
 * 命令对象:关机命令
 */
class TelevisionOffCommand implements Command {
    private Television television;

    public TelevisionOffCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.off();
    }

    @Override
    public void undo() {
        television.on();
    }
}

/**
 * 命令对象: 开机命令
 */
class TelevisionOnCommand implements Command{
    private Television television;

    public TelevisionOnCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.on();
    }

    @Override
    public void undo() {
        television.off();
    }
}