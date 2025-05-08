package com.guorong.stack.factory;

import java.util.Set;

/**
 * IStackProvider 提供者策略
 */
public interface IStackProviderStrategy {

    /**
     * 获取 IStackProvider 实例
     *
     * @param type 类型 {@link IStackProvider#getType()}
     * @return 返回 {@link IStackProvider} 实例
     */
    IStackProvider getProvider(String type);

    /**
     * 获取全部缓存 key {@link IStackProvider#getType()}
     *
     * @return 缓存key
     */
    Set<String> getProviderTypes();

    /**
     * 刷新缓存
     */
    void refresh();

    /**
     * 获取 IStackProvider的type
     *
     * @param provider {@link IStackProvider} 栈实例提供者
     * @return {@link IStackProvider#getType()} 栈实例提供者类型
     * @throws IllegalArgumentException {@link IStackProvider#getType()}为空值
     */
    default String getProviderType(IStackProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("provider cannot be null");
        }
        String type = provider.getType();
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("provider type cannot be null or empty, provider=%s", provider));
        }
        return type;
    }
}
