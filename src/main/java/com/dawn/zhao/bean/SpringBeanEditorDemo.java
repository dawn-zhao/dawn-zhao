package com.dawn.zhao.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanEditorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("spring-mvc.xml");
        xmlApplicationContext.refresh();
        Account account = (Account) xmlApplicationContext.getBean("account");
        System.out.println(account);
    }
}
