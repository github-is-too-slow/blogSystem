package com.billion.common.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author shimh
 * <p>
 * 2018年4月18日
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    String module() default "";

    String operation() default "";
}
