package ru.kushaevaa.math;

import java.util.HashMap;
import java.util.Map;

public class Lagrange extends Polynomial{
    private Map<Double, Double> d;
    public Lagrange(Map<Double, Double> d){
        this.d = new HashMap<>(d);
        var L = createLagrange();
        this.coeff = L.coeff.clone();
    }
    private Polynomial createLagrange(){
        Polynomial L = new Polynomial();
        for (double x : d.keySet()){
            L = L.plus(createFundamental(x).times(d.get(x)));
        }
        return L;
    }
    private Polynomial createFundamental(double x_k) {
        Polynomial L = new Polynomial(new double[]{1});
        for (double x_j : d.keySet()) {
            if (Math.abs(x_j - x_k) >= EPS) {
                try {
                    L = L.times(new Polynomial(new double[]{-x_j, 1}).div(x_k - x_j));
                }
                catch (Exception e) {}//так как ошибки и так не будет
            }
        }
        return L;
    }
}
