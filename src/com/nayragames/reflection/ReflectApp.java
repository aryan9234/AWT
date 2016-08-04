package com.nayragames.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * (c) 2013 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 05-10-2013
 *
 */

public class ReflectApp {

    public static void main(String args[]) {
        String parm=args[0];
        Class className=void.class;
        try {
            className=Class.forName(parm);
        }catch(ClassNotFoundException ex) {
            System.out.println("Not a Class or Interface");
            System.exit(0);
        }
        describeClassOrInterface(className,parm);
    }

    private static void describeClassOrInterface(Class className,String name) {

        displayModifiers(className.getModifiers());
        displayFields(className.getDeclaredFields());
        displayMethods(className.getDeclaredMethods());
        if(className.isInterface()) {
            System.out.println("Interface:"+name);
        }
        else{
            System.out.println("Class:"+name);}
            displayInterfaces(className.getInterfaces());
            displayConstructors(className.getDeclaredConstructors());
    }

    private static void displayModifiers(int m) {
        System.out.println("Modifiers:"+Modifier.toString(m));
    }

    private static void displayInterfaces(Class[] interfaces) {

        if(interfaces.length>0) {
            System.out.println("Interfaces:");
            for(int i=0;i<interfaces.length;i++)
                System.out.println(interfaces[i].getName());
            }
    }

    private static void displayFields(Field[] fields) {
        if(fields.length>0) {
            System.out.println("Fields:");
            for(int i=0;i<fields.length;i++)
                System.out.println(fields[i].toString());
        }
    }

    private static void displayConstructors(Constructor[] constructors) {
        if(constructors.length>0) {
            System.out.println("Constructor:");
            for(int i=0;i<constructors.length;i++)
                System.out.println(constructors[i].toString());
        }
    }

    private static void displayMethods(Method[] methods) {
        if(methods.length>0) {
            System.out.println("Methods:");
                for(int i=0;i<methods.length;i++)
                    System.out.println(methods[i].toString());
        }
    }
}