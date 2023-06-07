package com.minog.minog.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

@Slf4j
public class JpaInterceptor extends EmptyInterceptor {


    @Override
    public String onPrepareStatement(String sql) {
        log.info(sql);
        return sql;
    }

    @Override
    public boolean onFlushDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        log.info("qweqwe222");
        return false;
    }

    // EntityManager는 Autowired해서 사용하면 됨
    // private EntityManager em; 이런 방식으로

}


