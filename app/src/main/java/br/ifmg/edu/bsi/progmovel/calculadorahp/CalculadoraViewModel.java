package br.ifmg.edu.bsi.progmovel.calculadorahp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CalculadoraViewModel {

    private static final int NORMAL = 0;
    private static final int DIGITANDO = 1;
    private int status;
    private MutableLiveData<String> visor;
    private Calculadora calculadora;

    public CalculadoraViewModel() {
        status = NORMAL;
        calculadora = new Calculadora();
        visor = new MutableLiveData<>("0,00");
    }

    public LiveData<String> getVisor() {
        return visor;
    }

    // formatando o visor para numeros decimais
    private String formatVisor(double valor) {
        return String.format("%.2f", valor).replace(".", ",");
    }

    public void zero() {
        digita("0");
    }
    public void um()   {
        digita("1");
    }
    public void dois() {
        digita("2");
    }
    public void tres() {
        digita("3");
    }
    public void quatro(){
        digita("4");
    }
    public void cinco(){
        digita("5");
    }
    public void seis() {
        digita("6");
    }
    public void sete() {
        digita("7");
    }
    public void oito() {
        digita("8");
    }
    public void nove() {
        digita("9");
    }

    private void digita(String numero) {
        if (status == NORMAL) {
            visor.setValue(numero);
            status = DIGITANDO;
        } else {
            visor.setValue(visor.getValue() + numero);
        }
    }

    // conversão para numeros decimais
    public void decimal() {
        String valor = visor.getValue();
        if (status == NORMAL) {
            visor.setValue("0,");
            status = DIGITANDO;
        } else {
            if (!valor.contains(",")) {
                visor.setValue(valor + ",");
            }
        }
    }

    //função para apagar os dados
    public void apagar() {
        if (status == NORMAL) return;
        String valor = visor.getValue();
        if (valor != null && valor.length() > 1) {
            visor.setValue(valor.substring(0, valor.length() - 1));
        } else {
            visor.setValue("0,00");
            status = NORMAL;
        }
    }

    // função enter
    public void enter() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        calculadora.enter();
        status = NORMAL;
    }

    // função soma
    public void soma() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        calculadora.soma();
        visor.setValue(formatVisor(calculadora.getVisor()));
        status = NORMAL;
    }

    // função subtração
    public void subtrai() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        calculadora.subtrai();
        visor.setValue(formatVisor(calculadora.getVisor()));
        status = NORMAL;
    }

    // função multiplicação
    public void multiplica() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        calculadora.multiplica();
        visor.setValue(formatVisor(calculadora.getVisor()));
        status = NORMAL;
    }

    // função divisão
    public void divide() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        calculadora.divide();
        visor.setValue(formatVisor(calculadora.getVisor()));
        status = NORMAL;
    }

    // função para valor presente (pv no juros compostos)
    public void pv() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        if (calculadora.hasFV() && calculadora.hasI() && calculadora.hasN()) {
            calculadora.calculaPV();
            visor.setValue(formatVisor(calculadora.getVisor()));
        } else {
            calculadora.setPV(valor);
        }
        status = NORMAL;
    }

    // função para valor final (fv no juros compostos)
    public void fv() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        if (calculadora.hasPV() && calculadora.hasI() && calculadora.hasN()) {
            calculadora.calculaFV();
            visor.setValue(formatVisor(calculadora.getVisor()));
        } else {
            calculadora.setFV(valor);
        }
        status = NORMAL;
    }

    // função para a variavel i
    public void i() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        if (calculadora.hasPV() && calculadora.hasFV() && calculadora.hasN()) {
            calculadora.calculaI();
            visor.setValue(formatVisor(calculadora.getVisor()));
        } else {
            calculadora.setI(valor);
        }
        status = NORMAL;
    }

    // função para a variavel n
    public void n() {
        double valor = parseVisor();
        calculadora.setVisor(valor);
        if (calculadora.hasPV() && calculadora.hasFV() && calculadora.hasI()) {
            calculadora.calculaN();
            visor.setValue(formatVisor(calculadora.getVisor()));
        } else {
            calculadora.setN(valor);
        }
        status = NORMAL;
    }

    // formatação do visor
    private double parseVisor() {
        String valor = visor.getValue().replace(",", ".");
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
