package com.nayragames.game;

import java.awt.*;
import javax.swing.*;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 02-01-2014
 *
 */
public class Game extends JFrame {

    private Game game;
    private Canvas canvas;
    private int y=180;

    private Game(String label) {

        super(label);
        canvas=new Canvas(){
                public void paint(Graphics g) {
                       g.setColor(Color.blue);
                       g.setFont(new Font("Georgia",Font.BOLD,35));
                       g.drawString("EAGLE EYE",getToolkit().getScreenSize().width/2-120,50);
                }
        };

        canvas.setBackground(Color.black);

        JPanel mainPanel=new JPanel(new GridLayout(1,3));
        mainPanel.add(canvas);
        mainPanel.add(new Canvas());
        mainPanel.add(new Canvas());

        add(mainPanel,BorderLayout.CENTER);
    }

    private Game() {

        this("Game");
        game=this;
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
    }

    private void addStar() {

        Thread thread=new Thread(){
                            public void run(){
                                    Graphics g=canvas.getGraphics();
                                    g.setColor(Color.blue);
                                    g.setFont(new Font("Georgia",Font.BOLD,20));
                                    int i=getToolkit().getScreenSize().width/2-120;
                                    while(i<getToolkit().getScreenSize().width/2+90){
                                        g.drawString("*",i,70);
                                        try{
                                            Thread.sleep(500);
                                        }catch(Exception e){
                                            System.out.println(e);
                                        }
                                        i=i+10;
                                    }
                            }
        };
        thread.start();
    }

    private void print(String string){
        int x=300;
        Graphics g=canvas.getGraphics();
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

    public static void main(String...s) {
        new Game();
    }
}