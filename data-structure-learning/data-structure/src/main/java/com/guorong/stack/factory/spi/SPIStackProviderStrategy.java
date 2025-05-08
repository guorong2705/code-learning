package com.guorong.stack.factory.spi;

import com.guorong.stack.factory.IStackProvider;
import com.guorong.stack.factory.IStackProviderStrategy;

import java.util.Collections;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

/**
 * 使用SPI技术 管理 IStackProvider 实例的缓存，负责加载，刷新和并发访问控制
 */
public class SPIStackProviderStrategy implements IStackProviderStrategy {

    /**
     * 日志记录器
     */
    private static final Logger LOGGER = Logger.getLogger(SPIStackProviderStrategy.class.getName());

    /**
     * IStackProvider 缓存
     */
    private final ConcurrentHashMap<String, IStackProvider> providerCache = new ConcurrentHashMap<>();

    /**
     * 读写锁(用于控制并发读写)
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public SPIStackProviderStrategy() {
        refresh(); // 创建实例时候，注册SPI服务缓存
    }

    /**
     * 获取 IStackProvider
     *
     * @param type {@link IStackProvider#getType()} 返回值
     * @return {@link IStackProvider} 实例
     */
    @Override
    public IStackProvider getProvider(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }
        readWriteLock.readLock().lock();
        try {
            IStackProvider provider = providerCache.get(type);
            if (provider == null) {
                throw new IllegalArgumentException("provider type not found: " + type);
            }
            return provider;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 获取全部缓存 key
     *
     * @return 返回不能修改的缓存 key
     */
    @Override
    public Set<String> getProviderTypes() {
        readWriteLock.readLock().lock();
        try {
            return Collections.unmodifiableSet(providerCache.keySet());
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 刷新缓存
     */
    @Override
    public void refresh() {
        readWriteLock.writeLock().lock();
        try {
            providerCache.clear(); // 清空缓存
            LOGGER.info("Clear provider cache finish");

            // 获取所有注册SPI的 IStackProvider 服务
            ServiceLoader<IStackProvider> serviceLoader = ServiceLoader.load(IStackProvider.class);
            for (IStackProvider provider : serviceLoader) {
                // 获取 IStackProvider 类型
                LOGGER.info("Loading provider: " + provider.getType());
                String type = getProviderType(provider);
                // 验证是否重复
                if (providerCache.containsKey(type)) {
                    throw new IllegalArgumentException("duplicate provider type: " + type);
                }
                providerCache.put(type, provider);
            }
            if (providerCache.isEmpty()) {
                throw new IllegalStateException("IStackProvider service not found in SPI");
            }
            LOGGER.info("finish loading spi service");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


}
