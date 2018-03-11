package com.dawn.zhao.bean.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IntegerPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //加100，重写Spring 的bean属性Integer类型的注入
        setValue(Integer.parseInt(text)+100);
    }
}
