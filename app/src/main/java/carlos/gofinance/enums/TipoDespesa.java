package carlos.gofinance.enums;

/**
 * Created by Carlos on 10/06/2017.
 */
public enum TipoDespesa {
    LAZER(0),
    LOJA(1),
    SUPERMERCADO(2),
    RESTAURANTE(3),
    REFEICAO(4),
    ANIMAL(5),
    CARRO(6),
    SAUDE(7),
    BEBIDA(8),
    OUTRO(9);

    private int idDespesa;

    TipoDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public static TipoDespesa valueOf(int idDespesa) {
        for(TipoDespesa despesa : TipoDespesa.values()) {
            if(despesa.getIdDespesa() == idDespesa)
                return despesa;
        }

        return null;
    }
}
