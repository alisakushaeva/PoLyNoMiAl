package ru.kushaevaa.graphics;

import ru.kushaevaa.Converter;
import ru.kushaevaa.math.Newton;
import ru.kushaevaa.math.Polynomial;

import java.awt.*;
import java.util.HashMap;

public class DerivativePainter implements Painter{

    private Color clr;
    private Converter cnv;
    private HashMap<Double, Double> points;//координаты точек
    private boolean check;

    public DerivativePainter(Converter cnv, HashMap<Double,Double> points, Color color, boolean check){
        this.points = new HashMap<>(points);
        this.clr = color;
        this.cnv = cnv;
        this.check = check;
    }
    @Override
    public void paint(Graphics g, int width, int height) {
        if(check) {
            g.setColor(clr);
            Polynomial p = der();
            if (points.size() > 0) {
                for (int i = 0; i < width - 1; i++) {
                    double x1Crt = cnv.xScr2Crt(i);
                    double y1Crt = p.invoke(x1Crt);
                    double x2Crt = cnv.xScr2Crt(i + 1);
                    double y2Crt = p.invoke(x2Crt);
                    g.drawLine(cnv.xCrt2Scr(x1Crt), cnv.yCrt2Scr(y1Crt), cnv.xCrt2Scr(x2Crt), cnv.yCrt2Scr(y2Crt));
                }
            }
        }
    }
    public void setColor(Color clr) {
        this.clr = clr;
    }
    public void setPoints(HashMap<Double, Double> p){
        this.points = p;
    }
    public Polynomial der(){
        Polynomial p = new Newton(points);
        return p.derivative();
    }
}
