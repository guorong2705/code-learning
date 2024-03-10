package com.guorong.facade.v1;


class App {
    public static void main(String[] args) {
        useFacade();
    }
    /**
     * 未使用外观模式时的代码
     */
    public static void unUseFacade() {
        new ControllerGenerator().generate();
        new ServiceGenerator().generate();
        new PersistentGenerator().generate();
    }
    /**
     * 使用外观模式代码
     */
    public static void useFacade() {
        Facade.getInstance().generate();
    }
}
