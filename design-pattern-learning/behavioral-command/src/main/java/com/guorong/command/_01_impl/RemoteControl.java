package com.guorong.command._01_impl;

/**
 * 遥控器
 */
class RemoteControl {

    /**
     * 命令数组的长度
     */
    private static final int COMMAND_LEN = 7;

    /**
     * 打开命令数组
     */
    private Command[] onCommands = new Command[COMMAND_LEN];

    /**
     * 关闭命令数组
     */
    private Command[] offCommands = new Command[COMMAND_LEN];

    /**
     * 撤销命令
     */
    private Command undoCommand;


    public RemoteControl() {
    }

    /**
     * 设置命令
     *
     * @param index
     * @param onCommand  打开命令
     * @param offCommand 关闭命令
     */
    public void setCommand(int index, Command onCommand, Command offCommand) {
        if (index < 0 || index >= COMMAND_LEN) {
            throw new IllegalArgumentException(String.format("可设置案件范围为0~%s", COMMAND_LEN - 1));
        }
        onCommands[index] = onCommand;
        offCommands[index] = offCommand;
    }

    /**
     * 开启按钮
     *
     * @param index
     */
    public void onButtonWasPushed(int index) {
        onCommands[index].execute();
        undoCommand = onCommands[index];
    }

    /**
     * 关闭按钮
     *
     * @param index
     */
    public void offButtonWasPushed(int index) {
        offCommands[index].execute();
        undoCommand = offCommands[index];
    }


    /**
     * 撤销操作
     */
    public void undo() {
        undoCommand.undo();
    }


}
