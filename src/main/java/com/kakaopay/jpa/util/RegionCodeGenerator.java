package com.kakaopay.jpa.util;

import com.kakaopay.jpa.entity.RegionEntity;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

// http://woowabros.github.io/study/2019/01/30/identifier_generator.html
// 지역 코드 생성기
public class RegionCodeGenerator extends SequenceStyleGenerator {
    private static final String REGION_CODE_PREFIX = "reg_";

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(StandardBasicTypes.LONG, params, serviceRegistry);
    }

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        if(o instanceof RegionEntity){
            Serializable serializable = super.generate(sessionImplementor, o);
            Long longValue = (Long)serializable;

            return String.format(REGION_CODE_PREFIX + "%016d", longValue);
        }

        return null;
    }

}
