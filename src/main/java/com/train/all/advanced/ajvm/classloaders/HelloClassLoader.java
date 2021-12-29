package com.train.all.advanced.ajvm.classloaders;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  使用自定义类加载器Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，
 *  此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 * @author: kennys.chai
 * @date: 2021/12/29
 */
@Slf4j
public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {

        try {
            Class helloClass=new HelloClassLoader().findClass("Hello");
            Constructor constructor=helloClass.getDeclaredConstructor();
            Object obj=constructor.newInstance();
            Method method=helloClass.getMethod("hello");
            method.invoke(obj);
            //调用后 Hello, classLoader!

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取加密文件所处位置，文件在resources下
        File file=new File(this.getClass().getResource("/advanced/jvm/Hello.xlass").getPath());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] byteArray=new byte[Long.valueOf(file.length()).intValue()];
        fileInputStream.read(byteArray);
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i]=(byte) (255-byteArray[i]);
        }
        return defineClass(name,byteArray,0,byteArray.length);
    }
}
