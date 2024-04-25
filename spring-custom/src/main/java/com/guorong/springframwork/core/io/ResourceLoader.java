package com.guorong.springframwork.core.io;

/**
 * 资源加载器接口
 */
public interface ResourceLoader {

    /**
     * 加载资源
     * @param location
     * @return
     */
    Resource getResource(String location);
}
