package com.guorong.mvc.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan(basePackageClasses = ServletConfig.class)
public class ServletConfig {

}
