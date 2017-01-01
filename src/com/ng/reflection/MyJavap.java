package com.ng.reflection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;

/**
 * (c) 2013 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 25-11-2013
 *
 */

class MyJavap extends JFrame implements ActionListener {

    private JTextField tf;
    private JButton button,clear;
    private JTextArea jt[]=new JTextArea[5];

    private MyJavap() {

        super("MyJavap");
        JScrollPane js[]=new JScrollPane[5];
        JPanel panel[]=new JPanel[7];
        String border[]={BorderLayout.NORTH,BorderLayout.WEST,BorderLayout.CENTER,BorderLayout.EAST,BorderLayout.SOUTH};
        String type[]={"Fields","Interface","Constructors","Class/Interface","Methods"};
        add(panel[5]=new JPanel(new BorderLayout()),BorderLayout.CENTER);
        for(int i=0;i<js.length;i++){
            panel[5].add(panel[i]=new JPanel(new BorderLayout()),border[i]);
            panel[i].add(new JLabel("<html><h3>"+type[i]+"</h3></html>",JLabel.CENTER),BorderLayout.NORTH);
            panel[i].add(js[i]=new JScrollPane(jt[i]=new JTextArea(10,20)),BorderLayout.CENTER);
            jt[i].setEditable(false);
        }

        add(panel[6]=new JPanel(),BorderLayout.NORTH);
        panel[6].add(new JLabel("ENTER CLASS NAME :"));
        panel[6].add(tf=new JTextField(15));
        panel[6].add(button=new JButton("Show"));
        button.addActionListener(this);
        panel[6].add(clear=new JButton("Clear"));
        clear.addActionListener(this);
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println(e);
        }
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Class className=void.class;
        for(int i=0;i<jt.length;i++)
            jt[i].setText(null);

        if(e.getActionCommand().equals("Show")){
            try {
                className=Class.forName(tf.getText());
            }catch(ClassNotFoundException ex) {
                jt[2].append("Not a Class or Interface");
            }
            describeClassOrInterface(className,tf.getText());
        }
    }

    private void describeClassOrInterface(Class className,String name) {
        displayModifiers(className.getModifiers());
        displayFields(className.getDeclaredFields());
        displayMethods(className.getDeclaredMethods());
        displayInterfaces(className.getInterfaces());
        displayConstructors(className.getDeclaredConstructors());

        if(className.isInterface())
            jt[3].append("Interface:"+name);
        else
            jt[3].append("Class:"+name);
    }

    private void displayModifiers(int m) {
        System.out.println("Modifiers:"+Modifier.toString(m));
    }

    private void displayFields(Field[] fields) {
        if(fields.length>0) {
            for(int i=0;i<fields.length;i++)
                jt[0].append(fields[i].toString()+"\n");
        }
    }

    private void displayInterfaces(Class[] interfaces) {
         if(interfaces.length>0) {
            for(int i=0;i<interfaces.length;i++)
                jt[1].append(interfaces[i].getName()+"\n");
         }
    }

    private void displayConstructors(Constructor[] constructors) {
            if(constructors.length>0) {
                for(int i=0;i<constructors.length;i++)
                    jt[2].append(constructors[i].toString()+"\n");
            }
    }

    private void displayMethods(Method[] methods) {
        if(methods.length>0) {
            for(int i=0;i<methods.length;i++)
                jt[4].append(methods[i].toString()+"\n");
        }
    }

    public static void main(String...s) {
        new MyJavap();
    }
}