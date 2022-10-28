package ru.kushaevaa.math;

public class Polynomial {
    protected double[] coeff;//массив коэффициентов
    public static final double EPS = 1e-50;

    public Polynomial() {//конструктор по умолчанию
        coeff = new double[1];
        coeff[0] = 0.0;
    }

    public Polynomial(double coeff[]) {//создание полинома по массиву коэффициентов
        this.coeff = coeff.clone();
        //this.coeff = coeff; присвоение без копирования, массивы будут по одному адресу
        //делаем так что если в конце массива нули(коэффициенты уничтожились при операциях) полимон правильный все равно
        correctPower();
    }

    public Polynomial(Polynomial p) {
        coeff = p.coeff.clone();
    }

    private void correctPower() {//делаем так что если в конце массива нули(коэффициенты уничтожились при операциях) полином правильный все равно
        int j = coeff.length;
        while (j > 1 && Math.abs(coeff[j - 1]) < EPS) {
            j--;
        }
        if (j < coeff.length) {
            var ncoeff = new double[j];
            System.arraycopy(coeff, 0, ncoeff, 0, j);
            coeff = ncoeff;
        }
    }


    public double get(int i) {//получение коэффициентов полинома
        if (i >= 0 && i < coeff.length)
            return coeff[i];
        return Double.NaN;//not a number
    }

    public Polynomial plus(Polynomial other) {//сложение полиномов
        double[] nc = new double[Math.max(this.coeff.length, other.coeff.length)];
        var minl = Math.min(this.coeff.length, other.coeff.length);
        for (int i = 0; i < minl; i++) {
            nc[i] = this.coeff[i] + other.coeff[i];
        }
        var longPol = (this.coeff.length > other.coeff.length) ? this : other;//ссылка на более длинный полином
        //for (int i = minl; i < nc.length; i++){
        //    nc[i] = longPol.coeff[i];}
        System.arraycopy(longPol.coeff, minl, nc, minl, nc.length - minl);//то же, что сверху, только корочe(копирование массива)
        return new Polynomial(nc);
    }

    public Polynomial times(double num) {//умножение полинома на число
        double[] nc = new double[coeff.length];
        for (int i = 0; i < coeff.length; i++) {
            nc[i] = coeff[i] * num;
        }
        return new Polynomial(nc);
    }

    public Polynomial minus(Polynomial other) {//вычитание полиномов
        return plus(other.times(-1));
    }

    public Polynomial div(double num) throws Exception {//деление полинома на число
        if (Math.abs(num) >= EPS) return times(1.0 / num);
        else throw new Exception("Деление на 0");
    }

    public Polynomial times(Polynomial other) {//умножение полинома на полином
        double[] nc = new double[coeff.length + other.coeff.length - 1];
        for (int i = 0; i < other.coeff.length; i++) {
            for (int j = 0; j < this.coeff.length; j++) {
                nc[i + j] += other.coeff[i] * this.coeff[j];
            }
        }
        return new Polynomial(nc);
    }

    public double invoke(double x) {//метод, который вычисляет значение полинома в точке
        double result = coeff[0];
        double p = x;
        for (int i = 1; i < coeff.length; i++) {
            result += coeff[i] * p;
            p *= x;
        }
        return result;
    }

    public int getPower() {//возвращает степень полинома
        return this.coeff.length - 1;
    }


    public String output() {//метод для последующего вывода полинома
        String s = new String();
        if (coeff.length == 1) {
            if (Math.round(coeff[0]) == coeff[0]) {
                s += (int) coeff[0];
                return s;
            } else {
                s += coeff[0];
                return s;
            }
        }
        for (int i = coeff.length - 1; i > 1; i--) {
            if (i == coeff.length - 1) {
                if (coeff[i] == -1) {
                    s += "-";
                } else if (coeff[i] != 1) {
                    if (Math.round(coeff[i]) == coeff[i]) {
                        s += (int) coeff[i];
                    } else {
                        s += coeff[i];
                    }
                }
                s += "x^" + i;
                continue;
            }
            if (coeff[i] > 0) {
                s += "+";
            }
            if (coeff[i] != 0) {
                if (coeff[i] == -1) {
                    s += "-";
                } else if (coeff[i] != 1) {
                    if (Math.round(coeff[i]) == coeff[i]) s += (int) coeff[i];
                    else s += coeff[i];
                }
                s += "x^" + i;
            }
        }
        if (coeff.length >= 2) {
            if (coeff[1] > 0 && coeff.length > 2) {
                s += "+";
            }
            if (coeff[1] != 0) {
                if (coeff[1] == -1) {
                    s += "-";
                } else if (coeff[1] != 1) {
                    if (Math.round(coeff[1]) == coeff[1]) s += (int) coeff[1];
                    else s += coeff[1];
                }
                s += "x";
            }
        }
        if (coeff[0] > 0) {
            s += "+";
        }
        if (coeff[0] != 0) {
            if (Math.round(coeff[0]) == coeff[0]) s += (int) coeff[0];
            else s += coeff[0];
        }
        return s;
    }

    public Polynomial derivative(){
        if(coeff.length == 1 || coeff.length == 0) return new Polynomial();
        double [] c = new double[coeff.length-1];
        for(int i = 0; i < coeff.length-1; i++){
            c[i] = coeff[i+1]*(i+1);
        }
        return new Polynomial(c);
    }
}
