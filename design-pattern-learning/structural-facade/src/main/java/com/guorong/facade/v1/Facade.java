package com.guorong.facade.v1;

/**
 * 生成子系统的 外观对象
 * @author guorong
 */
class Facade {

    private static final Facade facade = new Facade();

    private Facade() {}

    /**
     * 获取外观模式实例
     * @return
     */
    public static Facade getInstance() {
        return facade;
    }

    /**
     * 生成代码
     */
    public void generate() {
        // 生成控制层代码
        new ControllerGenerator().generate();
        // 生成逻辑层代码
        new ServiceGenerator().generate();
        // 生成持久层代码
        new PersistentGenerator().generate();
    }

}
