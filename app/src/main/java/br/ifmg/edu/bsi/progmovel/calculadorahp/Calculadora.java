package br.ifmg.edu.bsi.progmovel.calculadorahp;

public class Calculadora {
    private double operando;
    private double visor;

    private Double n;
    private Double i;
    private Double pv;
    private Double fv;

    public Calculadora() {
        operando = 0.0;
        visor = 0.0;
        n = null;
        i = null;
        pv = null;
        fv = null;
    }

    public void setVisor(double numero) {
        visor = numero;
    }

    public double getVisor() {
        return visor;
    }

    public void enter() {
        operando = visor;
    }

    public void soma() {
        operando = visor + operando;
        visor = operando;
    }

    public void subtrai() {
        operando = visor - operando;
        visor = operando;
    }

    public void multiplica() {
        operando = visor * operando;
        visor = operando;
    }

    public void divide() {
        operando = operando / visor;
        visor = operando;
    }

    public void setN(double n) {
        this.n = n;
    }
    public void setI(double i) {
        this.i = i;
    }
    public void setPV(double pv) {
        this.pv = pv;
    }
    public void setFV(double fv) {
        this.fv = fv;
    }

    public Double getN() {
        return n;
    }
    public Double getI() {
        return i;
    }
    public Double getPV() {
        return pv;
    }
    public Double getFV() {
        return fv;
    }

    public boolean hasN()  {
        return n != null;
    }
    public boolean hasI()  {
        return i != null;
    }
    public boolean hasPV() {
        return pv != null;
    }
    public boolean hasFV() {
        return fv != null;
    }

    // fv = pv * (1 + i)^n
    public void calculaFV() {
        if (pv != null && i != null && n != null) {
            fv = pv * Math.pow(1 + i, n);
            visor = fv;
        }
    }

    // PV = FV / (1 + i)^n
    public void calculaPV() {
        if (fv != null && i != null && n != null) {
            pv = fv / Math.pow(1 + i, n);
            visor = pv;
        }
    }

    // i = (FV / PV)^(1/n) - 1
    public void calculaI() {
        if (pv != null && fv != null && n != null) {
            if (pv != 0 && n != 0) {
                i = Math.pow(fv / pv, 1.0 / n) - 1;
                visor = i;
            } else {
                visor = 0.0;
            }
        }
    }

    // n = log(FV / PV) / log(1 + i)
    public void calculaN() {
        if (pv != null && fv != null && i != null) {
            if (pv != 0 && i != -1) {
                n = Math.log(fv / pv) / Math.log(1 + i);
                visor = n;
            } else {
                visor = 0.0;
            }
        }
    }
}
