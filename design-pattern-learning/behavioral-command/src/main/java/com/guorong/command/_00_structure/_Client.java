package com.guorong.command._00_structure;

/**
 * 命令模式：
 */
class _Client {
    public static void main(String[] args) {
        // 命令对象
        Command command = new ConcreteCommand(new Receiver());
        // 请求者
        Invoker invoker = new Invoker(command);
        invoker.invoke();

        
    }
}

// 抽象命令
interface Command {
    void execute();
}

// 具体命令
class ConcreteCommand implements Command {
    private final Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

// 接收者
class Receiver {
    void action() {
        System.out.println(this.getClass().getSimpleName() + " 执行了action .....");
    }
}

// 调用者
class Invoker {

    private final Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void invoke() {
        command.execute();
    }
}

















