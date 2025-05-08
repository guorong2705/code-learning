package com.guorong.stack.factory;

import com.guorong.stack.IStack;

import java.util.Set;

/**
 * IStack工厂, 负责创建IStack实例
 */
public class StackFactory {

    /**
     * IStackProvider 缓存管理类
     */
    private final IStackProviderStrategy providerStrategy;


    public StackFactory(IStackProviderStrategy providerStrategy) {
        this.providerStrategy = providerStrategy;
    }

    /**
     * 创建栈实例
     *
     * @param clazz    栈实现类 Class
     * @param <T>      栈中元素类型
     * @param capacity 栈容量
     * @return 栈实例
     * @throws IllegalArgumentException clazz为 null
     */
    public <T> IStack<T> createStack(Class<? extends IStack> clazz, int capacity) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        return createStackInstance(clazz.getName(), capacity);
    }

    /**
     * 根据栈实现类的全限定名称，创建栈实例
     *
     * @param className 栈实现类的全限定类名
     * @param <T>       栈存储元素类型
     * @param capacity  栈容量
     * @return 栈实现类对象
     * @throws IllegalArgumentException 如果类名无效或者未注册
     */
    public <T> IStack<T> createStack(String className, int capacity) {
        return createStackInstance(className, capacity);
    }


    /**
     * 获取所有已注册的栈实现类实例提供者
     *
     * @return 已注册的栈实现类实例提供者名称
     */
    public Set<String> getAvailableStacks() {
        return providerStrategy.getProviderTypes();
    }


    /**
     * 刷新获取所有已注册的栈实现类实例提供者
     */
    public void refresh() {
        providerStrategy.refresh();
    }

    /**
     * 创建 IStack实例
     *
     * @param className IStack实现类全限定类名
     * @param <T>       栈存储类型
     * @param capacity  栈容量
     * @return IStack 实例
     */
    private <T> IStack<T> createStackInstance(String className, int capacity) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("className cannot be null or empty");
        }
        // 从缓存获取 IStackProvider
        IStackProvider provider = providerStrategy.getProvider(className);
        if (provider == null) {
            throw new IllegalArgumentException(String.format("type cannot found, type=%s", className));
        }
        return provider.createStack(capacity);
    }
}
