package carlos.gofinance.models;

import java.io.Serializable;

/**
 * Created by Carlos on 21/05/2017.
 */

public class Receita implements Serializable {
    private int idReceita;
    private double valorReceita;
    private String dataReceita;
    private int fonteReceita;
    private String tituloReceita;
    private int idConta;
    private int idCartao;

    public Receita(int idReceita, double valorReceita, String dataReceita, int fonteReceita, String tituloReceita, int idConta, int idCartao) {
        this.idReceita = idReceita;
        this.valorReceita = valorReceita;
        this.dataReceita = dataReceita;
        this.fonteReceita = fonteReceita;
        this.tituloReceita = tituloReceita;
        this.idConta = idConta;
        this.idCartao = idCartao;
    }

    public int getIdReceita() {
        return idReceita;
    }

    public double getValorReceita() {
        return valorReceita;
    }

    public String getDataReceita() {
        return dataReceita;
    }

    public int getFonteReceita() {
        return fonteReceita;
    }

    public String getTituloReceita() {
        return tituloReceita;
    }

    public int getIdConta() {
        return idConta;
    }

    public int getIdCartao() {
        return idCartao;
    }
}
