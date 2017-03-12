package com.ootb.service.logger;

import java.lang.reflect.Field;
import java.util.List;

import com.ootb.service.logger.type.InjectLogger;
import net.vidageek.mirror.dsl.Mirror;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Adam on 2017-03-12.
 */
@Component
public class LoggerPostProcessorService implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        List<Field> fields = new Mirror().on(bean.getClass()).reflectAll().fields();
        for (Field field : fields) {
            if (Logger.class.isAssignableFrom(field.getType()) && new Mirror().on(field).reflect().annotation(InjectLogger.class) != null) {
                new Mirror().on(bean).set().field(field).withValue(LoggerFactory.getLogger(bean.getClass()));
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
