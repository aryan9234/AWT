package com.ng.clock;

import java.awt.*;
import java.util.Calendar;

/**
 * (c) 2014 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 18-01-2014
 *
 */
class Sec extends Canvas implements Runnable {

    Sec can;
    double TWO_PI = 2.0 * Math.PI;
    Calendar nw = Calendar.getInstance();
    int width=300,hight=300;
    int xcent= width / 2,ycent= hight / 2;
    int minhand,maxhand;
    double rdns;
    int dxmin,dymin,dxmax,dymax;
    double radins,sine,cosine;
    double fminutes;

    Sec()
    {
        this.can=this;
    }

    public void paint(Graphics g) {

        int hours   = nw.get(Calendar.HOUR);
        int minutes = nw.get(Calendar.MINUTE);
        int seconds = nw.get(Calendar.SECOND);
        int millis  = nw.get(Calendar.MILLISECOND);
        System.out.println(millis);
        minhand=width /8;
        maxhand=width /2;
        rdns= (seconds + ((double)millis/1000)) / 60.0;
        drw(g,rdns,0,maxhand);
        //g.drawString(hours + ":" + minutes + ":" + seconds,  minhand+90, maxhand+60);
        minhand = 0;                    // Minute hand starts in middle.
        maxhand = width / 3;
        fminutes = (minutes + rdns) / 60.0;
        drw(g, fminutes, 0, maxhand);
        minhand = 0;                    // Minute hand starts in middle.
        maxhand = width / 4;
        drw(g, (hours + fminutes) / 12.0, 0, maxhand);

        g.drawOval(0,0, width, hight);
        g.drawString("9", xcent-145, ycent -0);
        g.drawString("3", xcent+140, ycent -0);
        g.drawString("6",xcent , ycent+145);
        g.drawString("12",xcent , ycent-135);
    }

    @Override
    public void run() {

        Graphics g=can.getGraphics();
        while(true) {
            try {
                updateTime();
                can.repaint();
                Thread.sleep(1000);
            }catch(InterruptedException e) {}
        }
    }

    private void updateTime() {
        nw.setTimeInMillis(System.currentTimeMillis());
    }

    private void drw(Graphics g,double prct,int minRadius, int maxRadius) {

        radins= (0.5 - prct)* TWO_PI;
        System.out.println(sine   = Math.sin(radins));
        cosine = Math.cos(radins);
        dxmin = xcent + (int)(minRadius * sine);
        dymin = ycent + (int)(minRadius * cosine);

        dxmax = xcent + (int)(maxRadius * sine);
        dymax = ycent + (int)(maxRadius * cosine);
        g.drawLine(dxmin, dymin, dxmax, dymax);
    }
}