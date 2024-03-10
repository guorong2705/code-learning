package com.guorong.command._01_impl;

/**
 * 命令模式
 */
class App {
    public static void main(String[] args) {
        // 遥控器
        RemoteControl remoteControl = new RemoteControl();
        // 客厅电灯
        Light light = new Light("客厅");
        int lightKey = 1; // 遥控器控制电灯按键
        // 命令
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        // 设置命令
        remoteControl.setCommand(lightKey, lightOnCommand, lightOffCommand);
        // 操作客厅灯光
        remoteControl.onButtonWasPushed(lightKey);
        remoteControl.offButtonWasPushed(lightKey);
        remoteControl.undo(); // 撤销关灯操作

        System.out.println("===========================================================");

        Television television = new Television("客厅");
        int tvKey = 2;
        // 设置命令
        TelevisionOnCommand televisionOnCommand = new TelevisionOnCommand(television);
        TelevisionOffCommand televisionOffCommand = new TelevisionOffCommand(television);
        remoteControl.setCommand(tvKey, televisionOnCommand, televisionOffCommand);
        // 操作电视
        remoteControl.onButtonWasPushed(tvKey);
        remoteControl.offButtonWasPushed(tvKey);
    }
}
