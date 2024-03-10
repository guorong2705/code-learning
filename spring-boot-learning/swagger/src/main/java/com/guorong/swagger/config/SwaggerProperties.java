package com.guorong.swagger.config;

/**
 * Swagger自定义配置
 */
public class SwaggerProperties {

    /**
     * 是否启用swagger
     */
    private Boolean enable;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 项目版本号
     */
    private String version;

    /**
     * 项目描述信息
     */
    private String description;

    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系人url
     */
    private String contactUrl;
    /**
     * 联系人email
     */
    private String contactEmail;

    /**
     * 获取构造器
     *
     * @return
     */
    public static SwaggerPropertiesBuilder builder() {
        return new SwaggerPropertiesBuilder();
    }

    // 构造器
    public static class SwaggerPropertiesBuilder {
        private Boolean enable = true;
        private String applicationName;
        private String version;
        private String description;
        private String contactName;
        private String contactUrl;
        private String contactEmail;

        public SwaggerPropertiesBuilder enable(Boolean enable) {
            this.enable = enable;
            return this;
        }

        public SwaggerPropertiesBuilder applicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public SwaggerPropertiesBuilder version(String version) {
            this.version = version;
            return this;
        }

        public SwaggerPropertiesBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SwaggerPropertiesBuilder contactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public SwaggerPropertiesBuilder contactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public SwaggerPropertiesBuilder contactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
            return this;
        }

        public SwaggerProperties build() {
            SwaggerProperties swaggerProperties = new SwaggerProperties();
            swaggerProperties.setEnable(this.enable);
            swaggerProperties.setApplicationName(this.applicationName);
            swaggerProperties.setVersion(this.version);
            swaggerProperties.setDescription(this.description);
            swaggerProperties.setContactName(this.contactName);
            swaggerProperties.setContactEmail(this.contactEmail);
            swaggerProperties.setContactUrl(this.contactUrl);
            return swaggerProperties;
        }
    }

    public Boolean getEnable() {
        return enable;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
