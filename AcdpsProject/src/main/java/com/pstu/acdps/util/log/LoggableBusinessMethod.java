/**
 * 
 */
package com.pstu.acdps.util.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a method with this annotation if you want it to be logged by
 * {@link BusinessMethodLogger}. Parameters are
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggableBusinessMethod {
    /**
     * This is a set of classes that information about this method being called
     * will be logged with. By default it is only {@link FileLogger}.
     * 
     * @return
     */
    Class<? extends Logger>[] logWith() default {DatabaseLogger.class};

    /**
     * Method name
     * 
     * @return
     */
    String method() default "";
}
