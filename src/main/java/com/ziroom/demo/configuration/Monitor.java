package com.ziroom.demo.configuration;

import java.lang.annotation.*;

/**
 * @author wangpeng
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Monitor {
}
