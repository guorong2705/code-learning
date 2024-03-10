package com.guorong.template_method._00_structure;


abstract class AbstractClass {

    /**
     * 模板方法(稳定点)
     */
    public final void templateMethod() {
        operation1();
        operation2();
    }

    /**
     * 抽象子步骤 1 (变化点)
     */
    protected abstract void operation1();

    /**
     * 抽象子步骤 2 (变化点)
     */
    protected abstract void operation2();
}
