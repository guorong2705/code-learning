package com.guorong.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.guorong.mp.entity.BaseEntity;
import com.guorong.mp.enums.DeleteStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * mybatis-plus提供的自动填充处理器，需要在字段中声明  @TableField(.. fill = FieldFill.INSERT)
 */
@Slf4j
@Component
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {

    // 创建时间字段名称
    public static final String CREATE_TIME_FIELD_NAME = "createTime";

    // 更新时间字段名称
    public static final String UPDATE_TIME_FIELD_NAME = "updateTime";

    // 删除标志字段名称
    public static final String DELETE_FLAG_FIELD_NAME = "deleteFlag";


    // 插入时候填充字段
    @Override
    public void insertFill(MetaObject metaObject) {
        if (!isBaseEntityType(metaObject)) {
            return;
        }
        log.info("自动填充插入 ---------------->>>");
        // 逻辑删除字段
        this.strictInsertFill(metaObject, DELETE_FLAG_FIELD_NAME, DeleteStatusEnum.class, DeleteStatusEnum.NOT_DELETE);

        // 创建时间字段
        Class<?> filedType = new Reflector(BaseEntity.class).getGetterType(CREATE_TIME_FIELD_NAME);
        if (filedType == LocalDateTime.class) {
            this.strictInsertFill(metaObject, CREATE_TIME_FIELD_NAME, LocalDateTime.class, LocalDateTime.now());
        } else if (filedType == Date.class) {
            this.strictInsertFill(metaObject, CREATE_TIME_FIELD_NAME, Date.class, new Date());
        }
    }


    // 更新时候填充字段
    @Override
    public void updateFill(MetaObject metaObject) {
        if (!isBaseEntityType(metaObject)) {
            return;
        }
        log.info("自动填充更新 ---------------->>>");
        // 更新时间字段
        Class<?> filedType = new Reflector(BaseEntity.class).getGetterType(UPDATE_TIME_FIELD_NAME);
        if (filedType == LocalDateTime.class) {
            this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD_NAME, LocalDateTime.class, LocalDateTime.now());
        } else if (filedType == Date.class) {
            this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD_NAME, Date.class, new Date());
        }
    }

    // 判断是否为BaseEntity类型
    private boolean isBaseEntityType(MetaObject metaObject) {
        if (BaseEntity.class.isInstance(metaObject.getOriginalObject())) {
            return true;
        }
        return false;
    }
}
