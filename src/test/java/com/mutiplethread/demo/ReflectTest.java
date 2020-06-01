package com.mutiplethread.demo;


import com.mutiplethread.demo.bean.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ReflectTest
 * @Description: TODO 反射测试
 * @Author lxc
 * @Date 2020/6/1 18:07
 * @Version V1.0
 **/
public class ReflectTest {

    @Test
    public void getPerson() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz1 = Class.forName("com.mutiplethread.demo.bean.Person");
        Person person = (Person) clazz1.newInstance();
        person.setPersonName("jack");
        person.setPersonAge(21);
        System.out.println(person.getPersonName()+"   "+person.getPersonAge());
    }

    @Test
    public void getPersonByConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz1 = Class.forName("com.mutiplethread.demo.bean.Person");
        Constructor constructor = clazz1.getConstructor(String.class,Integer.class);
        Person person = (Person) constructor.newInstance("Tom",21);
        System.out.println(person.getPersonName()+"  "+person.getPersonAge());
    }

    @Test
    public void getMethod() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz1 = Class.forName("com.mutiplethread.demo.bean.Person");
        Person p = (Person) clazz1.newInstance();
        p.setPersonName("jack");
        Method method = clazz1.getMethod("eat");
        method.invoke(p);
        System.out.println("=======================");
        Method method1 = clazz1.getMethod("eat",String.class,Integer.class);
        method1.invoke(p,"土豆",20);
    }
}
