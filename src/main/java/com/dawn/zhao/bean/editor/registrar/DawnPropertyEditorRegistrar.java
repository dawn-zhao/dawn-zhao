package com.dawn.zhao.bean.editor.registrar;

import com.dawn.zhao.bean.editor.DatePropertyEditor;
import com.dawn.zhao.bean.editor.IntegerPropertyEditor;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Date;


public class DawnPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        propertyEditorRegistry.registerCustomEditor(Date.class, new DatePropertyEditor());
        propertyEditorRegistry.registerCustomEditor(Integer.class, new IntegerPropertyEditor());
    }
}
