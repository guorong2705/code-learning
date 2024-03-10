package com.guorong.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现：
 */
class ProxyTest {

    @Test
    void test01() {
        Work teacher = new Teacher();
        ClassLoader classLoader = teacher.getClass().getClassLoader();
        Class<?>[] interfaces = teacher.getClass().getInterfaces();
        InvocationHandler invocationHandler = new WorkInvocationHandler(teacher);
        // 代理对象
        Work proxy = (Work) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        proxy.work();
    }


    interface Work {
        void work();
    }

    static class Teacher implements Work {
        @Override
        public void work() {
            System.out.println("老师的工作是教书 --->>>");
        }
    }

    static class WorkInvocationHandler implements InvocationHandler {
        // 被代理对象
        private Object target;
        public WorkInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> declaringClass = method.getDeclaringClass();
            System.out.println(String.format("%s 的方法 %s 被代理了", declaringClass.getName(), method.getName()));
            Object methodReturnResult = method.invoke(target, args);
            return methodReturnResult;
        }
    }

}
