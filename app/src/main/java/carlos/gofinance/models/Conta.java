package carlos.gofinance.models;

import java.io.Serializable;

/**
 * Created by Carlos on 21/05/2017.
 */

public class Conta implements Serializable {
    private int idConta;
    private double valorConta;
    private int tipoConta;
    private int corConta;
    private String nomeBanco;

    public Conta(int idConta, double valorConta, int tipoConta, int corConta, String nomeBanco) {
        this.idConta = idConta;
        this.valorConta = valorConta;
        this.tipoConta = tipoConta;
        this.corConta = corConta;
        this.nomeBanco = nomeBanco;
    }

    public int getIdConta() {
        return idConta;
    }

    public double getValorConta() {
        return valorConta;
    }


    public int getTipoConta() {
        return tipoConta;
    }

    public int getCorConta() {
        return corConta;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }
}
