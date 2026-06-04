package com.mizore.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mizore.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入填充...");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);

        // 从当前线程上下文中获取用户ID（你项目中的 BaseContext）
        Long currentId = BaseContext.getCurrentId();
        this.strictInsertFill(metaObject, "createUser", () -> currentId, Long.class);
        this.strictInsertFill(metaObject, "updateUser", () -> currentId, Long.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新填充...");
        // strictUpdateFill 只在字段为 null 时填充，如果想每次更新都覆盖，用 setFieldValByName
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);

        Long currentId = BaseContext.getCurrentId();
        this.setFieldValByName("updateUser", currentId, metaObject);
    }


}