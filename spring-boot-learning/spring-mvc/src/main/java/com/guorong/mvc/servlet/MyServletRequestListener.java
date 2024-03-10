package com.guorong.mvc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet请求监听器(监听所有servlet请求)
 */
@Slf4j
@WebListener("MyServletRequestListener")
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("{} --->>> requestInitialized", getClass().getName());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("{} --->>> requestDestroyed", getClass().getName());
    }
}
