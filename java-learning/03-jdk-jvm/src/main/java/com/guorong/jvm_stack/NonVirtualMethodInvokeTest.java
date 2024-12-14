package com.guorong.jvm_stack;

/**
 * 非虚方法调用演示：可以在编译器确定方法
 */
class NonVirtualMethodInvokeTest {

    // 静态方法调用: 字节码指令: invokestatic
    public void testStaticMethodInvoke() {
        StaticResolution.sayHello();
    }

    // 实例方法调用(就是调用构造函数): 字节码指令: invokespecial
    public void testInstanceMethodInvoke() {
        new StaticResolution();
    }

    // 始有方法调用： 字节码指令：invokespecial
    public void testPrivateMethod() {
        privateMethod();
    }

    // 父类方法调用(Object类): 字节码指令：invokevirtual
    public void testParentClassMethodInvoke(){
        hashCode();
    }



    
    private void privateMethod() {
        System.out.println("始有方法");
    }

    public static class StaticResolution{
        public static void sayHello() {
            System.out.println("hello world");
        }
    }

}
