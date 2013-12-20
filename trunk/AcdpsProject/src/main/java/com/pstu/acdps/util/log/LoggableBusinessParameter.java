package com.pstu.acdps.util.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для параметров методов аннотированных LoggableBusinessMethod.
 * Ставится над параметрами значения которых необходимо логировать.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggableBusinessParameter {

    /**
     * Имя параметра. Т.е. в базу будет сохранено 'имя параметра= значение параметра'
     * @return
     */
    String value();
}
