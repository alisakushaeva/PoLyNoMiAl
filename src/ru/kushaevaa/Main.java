package ru.kushaevaa;

import ru.kushaevaa.gui.MainWindow;
import ru.kushaevaa.math.Lagrange;
import ru.kushaevaa.math.Newton;
import ru.kushaevaa.math.Polynomial;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
       /* //var x = new double[]{1,2,3};
        //Polynomial p1 = new Polynomial(x);
        //x[0] = 555;
        //System.out.println(x[0]);
        //System.out.println(p1.get(0));
        var x = new double[]{1, 2, 3, -5, 1.55};
        var y = new double[]{9, -3, 1, 5, -2};
        Polynomial p1 = new Polynomial(x);
        Polynomial p2 = new Polynomial(y);
        Polynomial p3 = p1.plus(p2);
        //System.out.println(p1.getPower());
        //System.out.println(p2.getPower());
        //System.out.println(p3.getPower());
        //System.out.println(p1.get(0));
        System.out.println(p1.output());
        System.out.println(p2.output());
        System.out.println(p3.output());
        Polynomial p4 = new Polynomial(new double[]{1,2});
        System.out.println(p4.output());
        Polynomial p5 = new Polynomial(new double[]{0,1});
        System.out.println(p5.output());
        System.out.println(p4.times(p5).output());
        try{
            System.out.println(p4.div(0).output());}
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        //тест Лагранжа
        var d = new HashMap<Double, Double>();
        d.put(-1.0,0.0);
        d.put(0.0,1.0);
        d.put(1.0,2.0);
        //d.put(2.0,9.0);
        Lagrange L = new Lagrange(d);
        System.out.println(L.output());
        //System.out.println(new Polynomial().output());
        //System.out.println(2.17-1.55);

        //проверяем полином ньютона
        Newton N = new Newton(d);
        System.out.println(N.output());
        N.addNode(2.0, 9.0);//меняется сам полином
        System.out.println(N.output());
        N.addNode(2.0, 9.0);//второй раз он не изменится при добавлении той же точки
        System.out.println(N.output());
        //проверить скорость создания полиномов
        var s = new HashMap<Double, Double>();
        //по нему создать плнм лагранжа и ньютона и сравнить время формирования одного и второго полинома
        //потом добавить еще одну точку плнм лагранжа перестроить полностью а в полином ньютона добавить одну точку
        double a = 0.;
        double b = 0.;
        for (int i = 0; i < 45; i++){
            s.put(a,b);
            a++;
            b++;
        }
        var startL1 = System.currentTimeMillis();
        Lagrange L1 = new Lagrange(s);
        var endL1 = System.currentTimeMillis();
        System.out.println();
        System.out.println("Время создания первого полинома Лагранжа");
        System.out.println(endL1-startL1);
        System.out.println();
        System.out.println("Первый полином Лагранжа");
        System.out.println(L1.output());
        var startN1 = System.currentTimeMillis();
        Newton N1 = new Newton(s);
        var endN1 = System.currentTimeMillis();
        System.out.println();
        System.out.println("Время создания первого полинома Ньютона");
        System.out.println(endN1-startN1);
        System.out.println();
        System.out.println("Первый полином Ньютона");
        System.out.println(N1.output());
        System.out.println();
        System.out.println("Разность во времени создания полиномов");
        System.out.println(Math.abs(endN1-startN1 - (endL1-startL1)));
        s.put(100., 56.);
        var startL2 = System.currentTimeMillis();
        Lagrange L2 = new Lagrange(s);
        var endL2 = System.currentTimeMillis();
        System.out.println();
        System.out.println("Время создания второго полинома Лагранжа");
        System.out.println(endL2-startL2);
        System.out.println();
        System.out.println("Второй полином Лагранжа");
        System.out.println(L2.output());
        var startN2 = System.currentTimeMillis();
        N1.addNode(100.,56.);
        var endN2 = System.currentTimeMillis();
        System.out.println();
        System.out.println("Время создания второго полинома Ньютона");
        System.out.println(endN2-startN2);
        System.out.println();
        System.out.println("Второй полином Ньютона");
        System.out.println(N1.output());
        System.out.println();
        System.out.println("Разность во времени создания полиномов");
        System.out.println(Math.abs(endN2-startN2 - (endL2-startL2)));

        /*HashMap<Pair, Double> map = new HashMap<>();
        map.put(new Pair(2,3), 4.5);
        map.put(new Pair(1,1), 45.9);//если разделенная разность от одного аргумента
        System.out.println(map.get(new Pair(2,3)));
        System.out.println(map.get(new Pair(3,3)));*/
        /*Polynomial p = new Polynomial(new double[]{1,2,3});
        Polynomial dp = p.Derivative();
        System.out.println(p.output());
        System.out.println(dp.output());*/
        var wnd = new MainWindow();
        wnd.setVisible(true);//видимость окна - true
    }
}
