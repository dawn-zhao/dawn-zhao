package com.dawn.zhao.demo;

import com.alibaba.fastjson.JSON;
import com.dawn.zhao.bean.ReportDto;
import com.dawn.zhao.workbook.Column;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FieldAnnotationDemo {

    public static void main(String[] args) {
        getFieldsName();
    }

    public static String[] getFieldsName(){

        Field[] fields = ReportDto.class.getDeclaredFields();
        String[] headers = new String[fields.length];
        headers = Arrays.stream(fields).filter(e -> e.getAnnotation(Column.class) != null)
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Column.class).ordered()))
                .map((f) -> {
                    return f.getAnnotation(Column.class).value();
                }).collect(Collectors.toList()).toArray(headers);

        System.out.println(headers);
        System.out.println(JSON.toJSONString(headers));
        return headers;
    }
}
