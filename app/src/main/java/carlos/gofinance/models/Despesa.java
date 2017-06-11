package carlos.gofinance.models;

import java.io.Serializable;

/**
 * Created by Carlos on 21/05/2017.
 */
public class Despesa implements Serializable {
    private int idDespesa;
    private double valorDespesa;
    private String dataDespesa;
    private int tipoDespesa;
    private char despesaPaga;
    private String tituloDespesa;
    private int idConta;
    private int idCartao;

    public Despesa(int idDespesa, double valorDespesa, String dataDespesa, int tipoDespesa, char despesaPaga,
                   String tituloDespesa, int idConta, int idCartao) {
        this.idDespesa = idDespesa;
        this.valorDespesa = valorDespesa;
        this.dataDespesa = dataDespesa;
        this.tipoDespesa = tipoDespesa;
        this.despesaPaga = despesaPaga;
        this.tituloDespesa = tituloDespesa;
        this.idConta = idConta;
        this.idCartao = idCartao;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public double getvalorDespesa() {
        return valorDespesa;
    }

    public String getDataDespesa() {
        return dataDespesa;
    }

    public int getTipoDespesa() {
        return tipoDespesa;
    }

    public char getDespesaPaga() {
        return despesaPaga;
    }

    public String getTituloDespesa() {
        return tituloDespesa;
    }

    public int getIdConta() {
        return idConta;
    }

    public int getIdCartao() {
        return idCartao;
    }
}
