package com.ng.eagleeye;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 02-01-2014
 *
 */

public class EagleEye extends JFrame {

    private Canvas canvas[]=new Canvas[3];
    private EagleEye eagleEye;
    private int y=180;
    private EagleEye(String label) {

        super(label);
        canvas[0]=new Canvas();
        canvas[1]=new Canvas(){
                        public void paint(Graphics g) {
                            g.setColor(Color.blue);
                            g.setFont(new Font("Georgia",Font.BOLD,35));
                            g.drawString("EAGLE EYE",20,50);
                        }};

        canvas[2]=new Canvas();
        JPanel mainPanel=new JPanel(new GridLayout(1,3));

        for(int i=0;i<canvas.length;i++){
            canvas[i].setBackground(Color.black);
            mainPanel.add(canvas[i]);
        }

        add(mainPanel,BorderLayout.CENTER);
    }

    private EagleEye() {
        this("EagleEye");
        eagleEye=this;
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println(e);
        }
        setBounds(0,0,getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        addStar();
        addTime();
        print("Welcome to Eagle Protocol...");
        print(" ");
        print("Now : "+new Date().toString());
        print(Locale.getDefault().toString());
        print("User : "+System.getProperty("user.name"));
        print(" ");
        print("Verification of Agent......");
        new Login(eagleEye);
    }

    private void addStar() {

        Thread thread=new Thread(){
                        public void run(){
                                Graphics g=canvas[1].getGraphics();
                                g.setColor(Color.blue);
                                g.setFont(new Font("Georgia",Font.BOLD,20));
                            int i=20;
                            while(i<200){
                                g.drawString("*",i,70);
                            try{
                                Thread.sleep(500);
                                }catch(Exception e){
                                    System.out.println(e);
                                }
                                i=i+10;
                            }
                        }};
        thread.start();
    }

    private void print(String string){
        int x=0;
        Graphics g=canvas[1].getGraphics();
        g.setColor(Color.white);
        g.setFont(new Font("Georgia",Font.BOLD,20));

        char ch[]=string.toCharArray();
        for(int i=0;i<ch.length;i++){
            g.drawString(""+ch[i],x,y);
            x=x+14;

            try{
                Thread.sleep(200);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        y=y+20;
    }

    private void addTime(){

        Thread time=new Thread(){
                    @Override
                    public void run(){
                        while(true) {
                            Graphics g=canvas[2].getGraphics();
                            canvas[2].update(g);

                            g.setColor(Color.white);
                            g.setFont(new Font("Georgia",Font.BOLD,20));
                            g.drawString(new Date().toString(),100,100);
                            try{
                                Thread.sleep(1000);
                            }catch(Exception e){}

                        }
                    }
        };
        time.start();
    }

    public static void main(String...s) {
        new EagleEye();
    }
}