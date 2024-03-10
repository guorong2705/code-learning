package com.guorong.mvc.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Component
public class PropertiesResourceLoaderAware implements ResourceLoaderAware {

    private static final String RESOURCE_PATH = "classpath:props/resourceLoaderAware.properties";

    // 存储配置文件属性值
    private static final Properties PROPERTIES = new Properties();

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        log.info("资源加载器的名称 --->>> {}", resourceLoader.getClass().getSimpleName());
        // 获取代表文件的资源
        Resource resource = resourceLoader.getResource(RESOURCE_PATH);
        // 判断文件物理是否存在
        if (!resource.exists()) {
            log.info("要加载的文件不存在 --->>> {}", RESOURCE_PATH);
            return;
        }
        try (InputStream inputStream = resource.getInputStream()) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.error("读取文件失败 --->>> ", e);
        }
    }

    /**
     * 获取配置文件中的属性
     * @return
     */
    public Properties getProperties() {
        return PROPERTIES;
    }



}
