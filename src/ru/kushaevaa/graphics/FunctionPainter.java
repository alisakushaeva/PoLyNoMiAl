package ru.kushaevaa.graphics;

import ru.kushaevaa.Converter;
import ru.kushaevaa.math.Newton;
import ru.kushaevaa.math.Polynomial;

import java.awt.*;
import java.util.HashMap;

public class FunctionPainter implements Painter {
    private Color clr;
    private Converter cnv;
    private HashMap<Double, Double> points;//координаты точек
    private boolean check;
    Newton poly;

    public FunctionPainter(Converter cnv, HashMap<Double,Double> points, Color color, boolean check){
        this.points = new HashMap<>(points);
        this.clr = color;
        this.cnv = cnv;
        this.check = check;
        poly = new Newton(points);
    }
    @Override
    public void paint(Graphics g, int width, int height) {
        if(check) {
            g.setColor(clr);
            if (points.size() > 0) {
                for (int i = 0; i < width - 1; i++) {
                    double x1Crt = cnv.xScr2Crt(i);
                    double y1Crt = poly.invoke(x1Crt);
                    double x2Crt = cnv.xScr2Crt(i + 1);
                    double y2Crt = poly.invoke(x2Crt);
                    g.drawLine(cnv.xCrt2Scr(x1Crt), cnv.yCrt2Scr(y1Crt), cnv.xCrt2Scr(x2Crt), cnv.yCrt2Scr(y2Crt));
                }
            }
        }
    }
    public void setColor(Color clr) {
        this.clr = clr;
    }
    /*public Newton poly(){
        return new Newton(points);
    }*/
    public void setPoints(HashMap<Double, Double> p){
        this.points = p;
        poly = new Newton(points);
    }
    public void addPoint(double x, double y){
        this.points.put(x,y);
        poly.addNode(x,y);
    }
}
