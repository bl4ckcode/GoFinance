package carlos.gofinance.models;

import java.io.Serializable;

/**
 * Created by Carlos on 21/05/2017.
 */
public class Cartao implements Serializable {
    private int idCartao;
    private double valorCartao;
    private int tipoCartao;
    private int corCartao;
    private String bandeiraCartao;

    public Cartao(int idCartao, double valorCartao, int tipoCartao, int corCartao, String bandeiraCartao) {
        this.idCartao = idCartao;
        this.valorCartao = valorCartao;
        this.tipoCartao = tipoCartao;
        this.corCartao = corCartao;
        this.bandeiraCartao = bandeiraCartao;
    }

    public int getIdCartao() {
        return idCartao;
    }

    public double getValorCartao() {
        return valorCartao;
    }

    public int getTipoCartao() {
        return tipoCartao;
    }

    public int getCorCartao() {
        return corCartao;
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }
}
