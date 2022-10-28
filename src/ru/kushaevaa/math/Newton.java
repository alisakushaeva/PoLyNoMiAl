package ru.kushaevaa.math;

import java.util.*;

public class Newton extends Polynomial {
    private List<Double> nodes;
    private List<Double> values;

    public Newton(Map<Double, Double> d) {
        nodes = new ArrayList<Double>(d.keySet());//список узлов
        values = new ArrayList<Double>(d.values());//список значений
        var N = createNewton();
        this.coeff = N.coeff.clone();
    }

    public Polynomial createNewton() {
        if(this.nodes.size() == 0) return new Polynomial();
        Polynomial N = new Polynomial(new double[]{values.get(0)});//создаем полином с начальным значением f(x_1)
        List<Double> x = new ArrayList<Double>();//для подсчёта коэффициентов
        x.add(nodes.get(0));
        List<Double> y = new ArrayList<Double>();//для подсчёта коэффициентов
        y.add(values.get(0));
        Polynomial wn = new Polynomial(new double[]{-nodes.get(0), 1});//х-х0
        for (int i = 1; i < nodes.size(); i++){
            x.add(nodes.get(i));
            y.add(values.get(i));
            N = N.plus(wn.times(createCoeff(x,y)));
            wn = wn.times(new Polynomial(new double[]{-nodes.get(i), 1}));
        }
        return N;

    }
    //List<Double> f = new ArrayList<Double>();//список для хранения разделенных разностей
    //индексы - целые числа - для подсчета разделенных разностей
    //f(x0,xk)= (f(x1,xk)-f(x0,xk-1))/(xk-x0)

    public double createCoeff(List<Double> nodes, List<Double> values) {//метод для вычисления коэффициентов полинома
        /*int i = nodes.indexOf(0);
        int j = nodes.size()-1;
        if (nodes.size() == 1) {
            if (f.get(new Pair(0,0)) == null) f.put(new Pair(0, 0), values.get(i));
            return f.get(new Pair(i, i));
        }
        else if (nodes.size() == 2) {
            if(f.get(new Pair(i,j)) == null) f.put(new Pair(i, j), (values.get(1)-values.get(0))/(nodes.get(1)-nodes.get(0)));
            return f.get(new Pair(i, i+1));
        }
        else {
            if(f.get(new Pair(i, j)) == null) f.put(new Pair(i,j), (createCoeff(nodes.subList(1, nodes.size()), values.subList(1, nodes.size()))-
                    createCoeff(nodes.subList(0, nodes.size() - 1), values.subList(0, nodes.size() - 1)))
                    / (nodes.get(nodes.size() - 1) - nodes.get(0)));
            return f.get(new Pair(i,j));
        }*/
        double f = 0;
        for (int i = 0; i < nodes.size(); i++){
        double p = 1;
            for(int j = 0; j < nodes.size(); j++){
                if (j!= i) p*= nodes.get(i) - nodes.get(j);
            }
            f += values.get(i)/p;
        }
        return f;
    }

    public void addNode(double x, double y) {//добавление узла
        Polynomial N = new Polynomial(this.coeff);
        if(nodes.indexOf(x) == -1) {//добавляем узел, если его еще нет в списке узлов
            nodes.add(x);
            values.add(y);
            Polynomial wn = new Polynomial(new double[]{1});
            for(int i = 0; i < nodes.size()-1; i++){
                wn = wn.times(new Polynomial(new double[]{-nodes.get(i), 1}));
            }
            N = N.plus(wn.times(createCoeff(nodes, values)));
            this.coeff = N.coeff;
        }
    }
}
