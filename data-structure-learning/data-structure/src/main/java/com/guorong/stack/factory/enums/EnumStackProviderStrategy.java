package com.guorong.stack.factory.enums;

import com.guorong.stack.factory.IStackProvider;
import com.guorong.stack.factory.IStackProviderStrategy;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 * 使用枚举缓存管理IStackProvider缓存
 */
public class EnumStackProviderStrategy implements IStackProviderStrategy {

    private static final Logger LOGGER = Logger.getLogger(EnumStackProviderStrategy.class.getName());

    /**
     * IStackProvider缓存
     */
    private final ConcurrentMap<String, IStackProvider> providerCache = new ConcurrentHashMap<>();

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public EnumStackProviderStrategy() {
        refresh();
    }

    /**
     * 手动注册 IStackProvider
     *
     * @param provider {@link IStackProvider}
     */
    public void registerProvider(IStackProvider provider) {
        try {
            String type = getProviderType(provider);
            providerCache.put(type, provider);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public IStackProvider getProvider(String type) {
        readWriteLock.readLock().lock();
        try {
            return providerCache.get(type);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


    @Override
    public Set<String> getProviderTypes() {
        readWriteLock.readLock().lock();
        try {
            return Collections.unmodifiableSet(providerCache.keySet());
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void refresh() {
        readWriteLock.writeLock().lock();
        try {
            providerCache.clear(); // 清空缓存
            LOGGER.info("clear provider cache");

            for (StackType provider : StackType.values()) {
                LOGGER.info("loading provider type: " + provider.getType());
                String type = getProviderType(provider);
                // 验证是否重复
                if (providerCache.containsKey(type)) {
                    throw new IllegalArgumentException("duplicate provider type: " + type);
                }
                providerCache.put(type, provider);
            }
            if (providerCache.isEmpty()) {
                throw new IllegalStateException("StackType not found instance: " + StackType.class.getName());
            }
            LOGGER.info("provider loading finish");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


}
