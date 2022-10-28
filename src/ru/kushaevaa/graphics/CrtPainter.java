package ru.kushaevaa.graphics;

import ru.kushaevaa.Converter;

import java.awt.*;

public class CrtPainter implements Painter {
    private int xCenter, yCenter;
    private  Converter cnv;

    public  CrtPainter(Converter cnv){
        this.cnv = cnv;
    }
    @Override
    public void paint(Graphics g, int width, int height) {
        xCenter = cnv.xCrt2Scr(0);
        yCenter = cnv.yCrt2Scr(0);
        g.setColor(Color.BLACK);
        g.drawLine(xCenter, 0, xCenter, height);
        g.drawLine(0, yCenter, width, yCenter);
        int th = 2;
        if (cnv.xMax * cnv.xMin < 0) {
            //рисуем штрихи на оси у
            for(double i = cnv.yMin; i < cnv.yMax; i+=0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if(Math.round(10*i)%10 == 0){
                    th+=4;
                    //вывести координату
                    if(Math.round(i)!=0){
                    g.drawString(Double.toString(Math.round(i)), xCenter+4, cnv.yCrt2Scr(i));}
                }
                else if(Math.round(10*i)%5==0){
                    th+=2;
                }
                g.drawLine(xCenter-th, cnv.yCrt2Scr(i), xCenter+th, cnv.yCrt2Scr(i));
                th = 2;
            }
        }
        else {
            //рисуем штрихи по бокам
            for(double i = cnv.yMin; i<cnv.yMax;i+=0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if(Math.round(10*i)%10 == 0){
                    th+=4;
                    //вывести координату
                    g.drawString(Double.toString(Math.round(i)), 4, cnv.yCrt2Scr(i));
                    g.drawString(Double.toString(Math.round(i)), width-25, cnv.yCrt2Scr(i));
                }
                else if(Math.round(10*i)%5==0){
                    th+=2;
                }
                g.drawLine(0, cnv.yCrt2Scr(i), th, cnv.yCrt2Scr(i));
                g.drawLine(width-th-1, cnv.yCrt2Scr(i), width, cnv.yCrt2Scr(i));
                th = 2;
            }
        }
        if (cnv.yMax * cnv.yMin < 0)  {
            //рисуем штрихи на оси х
            for(double i = cnv.xMin; i<cnv.xMax;i+=0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if(Math.round(10*i)%10 == 0){
                    th+=4;
                    //вывести координату
                    if(Math.round(i) != 0) g.drawString(Double.toString(Math.round(i)), cnv.xCrt2Scr(i)-8, yCenter-5);
                }
                else if(Math.round(10*i)%5 == 0){
                    th+=2;
                }
                g.drawLine(cnv.xCrt2Scr(i), yCenter-th, cnv.xCrt2Scr(i), yCenter+th);
                th = 2;
            }
        }
        else {
            //рисуем штрихи сверху и снизу
            for(double i = cnv.xMin; i<cnv.xMax;i+=0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if(Math.round(10*i)%10 == 0){
                    th+=4;
                    //вывести координату
                    g.drawString(Double.toString(Math.round(i)), cnv.xCrt2Scr(i)-8, 10);
                    g.drawString(Double.toString(Math.round(i)), cnv.xCrt2Scr(i)-8, height-8);
                }
                else if(Math.round(10*i)%5==0){
                    th+=2;
                }
                g.drawLine(cnv.xCrt2Scr(i), 0, cnv.xCrt2Scr(i), th);
                g.drawLine(cnv.xCrt2Scr(i), height-th-1, cnv.xCrt2Scr(i), height);
                th = 2;
            }
        }
        g.drawString("0.0", xCenter+20*(int)(Math.signum(cnv.xMin)* (int)Math.signum(cnv.xMax)),
                yCenter - 15*(int)Math.signum(cnv.yMin)*(int)Math.signum(cnv.yMax));
    }
}
