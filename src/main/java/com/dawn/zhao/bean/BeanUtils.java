//package com.dawn.zhao.bean;
//
//import com.dawn.zhao.bean.editor.DatePropertyEditor;
//import com.sun.beans.editors.IntegerEditor;
//import com.sun.beans.editors.StringEditor;
//
//import java.beans.*;
//import java.lang.reflect.Method;
//import java.util.Date;
//import java.util.stream.Stream;
//
//public class BeanUtils {
//
//    public static void main (String[] args) {
//
//        try {
//
////            BeanInfo beanInfo = Introspector.getBeanInfo(Account.class);
////            BeanInfo beanInfo = Introspector.getBeanInfo(Account.class, Object.class);
//            Class<?> beanClass = Class.forName("com.dawn.zhao.bean.Account");
//            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
//            BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
//            System.out.println(beanDescriptor);
//
//            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
////            Stream.of(methodDescriptors).forEach(methodDescriptor->{
////                System.out.println(methodDescriptor);
////            });
////
//            System.out.println("----------Method Descriptors------------");
//            Stream.of(methodDescriptors).forEach(System.out::println);
//
//            System.out.println("----------Property Descriptors------------");
//            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//            Stream.of(propertyDescriptors).forEach(System.out::println);
//
//            Account account = new Account();
//            Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
//                if(propertyDescriptor.getPropertyType() == String.class){
//                    propertyDescriptor.setPropertyEditorClass(StringEditor.class);
//                } else if (propertyDescriptor.getPropertyType() == int.class || propertyDescriptor.getPropertyType() == Integer.class){
//                    propertyDescriptor.setPropertyEditorClass(IntegerEditor.class);
//                } else if (propertyDescriptor.getPropertyType() == long.class || propertyDescriptor.getPropertyType() == Long.class){
//                    propertyDescriptor.setPropertyEditorClass(IntegerEditor.class);
//                } else if (propertyDescriptor.getPropertyType() == Date.class){
//                    propertyDescriptor.setPropertyEditorClass(DatePropertyEditor.class);
//                } else {
//                    return;
//                }
//
//                String propertyName = propertyDescriptor.getName();
//                Method method = propertyDescriptor.getWriteMethod();
//                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(account);
//                propertyEditor.addPropertyChangeListener(new SetPropertyChangeListener(account, method));
//
//                if("account".equals(propertyName)) {
//                    propertyEditor.setAsText("764562461");
//                } else if ("id".equals(propertyName)) {
//                    propertyEditor.setAsText("666");
//                } else if ("brithDay".equals(propertyName)) {
//                    propertyEditor.setAsText("2018-01-14 00:47:00");
//                }
//            });
//            System.out.println(account);
//        } catch (IntrospectionException e){
//            e.printStackTrace();
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    private static class SetPropertyChangeListener implements PropertyChangeListener{
//
//        private final Object bean;
//
//        private final Method setterMethod;
//
//        public SetPropertyChangeListener(Object bean, Method setterMethod) {
//            this.setterMethod = setterMethod;
//            this.bean = bean;
//        }
//
//        @Override
//        public void propertyChange(PropertyChangeEvent evt) {
//            PropertyEditor editorSource = (PropertyEditor) evt.getSource();
//            try {
//                setterMethod.invoke(bean, editorSource.getValue());
//            } catch (Exception e) {
//            }
//
//        }
//    }
//}
