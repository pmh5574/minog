package com.minog.minog.config;

import com.minog.minog.domain.User;
import org.hibernate.EmptyInterceptor;

import java.io.Serializable;
import java.lang.reflect.Type;

public class JpaInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id,
                          Object[] state, String[] propertyNames, Type[] types) {


        return super.onSave(entity, id, state, propertyNames, types);
    }
}
