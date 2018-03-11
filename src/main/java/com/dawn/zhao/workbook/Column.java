package com.dawn.zhao.workbook;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Gmy on 2017/6/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    String value();

    int ordered();

    /**
     * 日期或者数值类型的格式
     * @return
     */
    String format() default "";
}
