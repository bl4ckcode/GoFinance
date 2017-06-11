package carlos.gofinance.enums;

/**
 * Created by Carlos on 10/06/2017.
 */

public enum TipoCartao {
    CREDITO(0), DEBITO(1);

    private int idTipoCartao;

    TipoCartao(int idTipoCartao) {
        this.idTipoCartao = idTipoCartao;
    }

    public int getIdTipoCartao() {
        return idTipoCartao;
    }

    public static TipoCartao valueOf(int idTipoCartao) {
        for (TipoCartao cartao : TipoCartao.values()) {
            if (cartao.getIdTipoCartao() == idTipoCartao)
                return cartao;
        }

        return null;
    }
}
