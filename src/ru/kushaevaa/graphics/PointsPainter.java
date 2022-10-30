package ru.kushaevaa.graphics;

import ru.kushaevaa.Converter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PointsPainter implements Painter {
    private Color clr;
    private Converter cnv;
    private boolean check;

    private HashMap<Double, Double> points;//координаты точек
    public PointsPainter(Converter cnv, HashMap<Double,Double> points, Color color, boolean check){
        this.points = new HashMap<>(points);
        this.clr = color;
        this.cnv = cnv;
        this.check = check;
    }

    @Override
    public void paint(Graphics g, int width, int height) {
        if(check) {
            g.setColor(clr);
            for (Map.Entry<Double, Double> point : points.entrySet()) {
                int x = cnv.xCrt2Scr(point.getKey());
                int y = cnv.yCrt2Scr(point.getValue());
                g.fillOval(x-3, y-3, 6, 6);
            }
        }
    }

    public void addPoint(double x, double y) {
        this.points.put(x,y);
    }
    public void removePoint(double x, double y){this.points.remove(x,y);}

    public void setColor(Color clr) {
        this.clr = clr;
    }
}
