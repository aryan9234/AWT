package com.nayragames.clock;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.util.*;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 18-01-2014
 *
 */

class Analog extends JFrame {

    private Sec s1;
    private Analog ana;
    private JButton b;

    private Analog(Sec s1) {

        super("Clock");
        this.ana=this;
        this.s1=s1;
        add(s1,BorderLayout.CENTER);
        setSize(315,335);
        setVisible(true);
    }

    public static void main(String... s) {
        Sec s1=new Sec();
        Thread t1=new Thread(s1);
        new Analog(s1);
        t1.start();
    }
}