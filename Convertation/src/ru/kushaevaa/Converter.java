package ru.kushaevaa;

public class Converter {
    public double xMin, xMax, yMin, yMax;
    int width, height;

    public Converter(double xMin, double xMax, double yMin, double yMax, int width, int height){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
    }

    public double getXDen(){
        return width/(xMax-xMin);
    }
    public double getYDen(){
        return height/(yMax-yMin);
    }
    public int xCrt2Scr(double x){
        var v = (int)((x-xMin)*getXDen());
        if (v < -width) v = -width;
        if (v > 2*width) v = 2*width;
        return v;
    }

    public int yCrt2Scr(double y){
        var v = (int)((yMax-y)*getYDen());
        if (v < -height) v = -height;
        if (v > 2*height) v = 2*height;
        return v;
    }

    public double xScr2Crt(int x){
        return x/getXDen() + xMin;
    }

    public double yScr2Crt(int y){
        return yMax - y/getYDen();
    }

    public double xDistScr2Crt(int x1, int x2){
        return Math.abs(x1-x2)/getXDen();
    }
    public int xDistCrt2Scr(double x1, double x2){
        return (int)(getXDen()*Math.abs(x1-x2));
    }
    public int yDistCrt2Scr(double y1, double y2){
        return (int)(getYDen()*Math.abs(y1-y2));
    }

    public void setXEdges(Double xmin, Double xmax) {
        this.xMin = xmin;
        this.xMax = xmax;
    }

    public void setYEdges(Double ymin, Double ymax) {
        this.yMin = ymin;
        this.yMax = ymax;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
