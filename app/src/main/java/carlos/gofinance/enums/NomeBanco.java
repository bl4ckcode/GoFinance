package carlos.gofinance.enums;

/**
 * Created by Carlos on 10/06/2017.
 */
public enum NomeBanco {
    CAIXA_ECONOMICA(0),
    BB(1),
    BNDES(2),
    CITIBANK(3),
    INTERMEDIUM(4),
    ITAU(5),
    MERCANTIL(6),
    SANTANDER(7),
    BRADESCO(8),
    OUTRO(9);

    private int idBanco;

    NomeBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public static NomeBanco valueOf(int idBanco) {
        for (NomeBanco banco : NomeBanco.values()) {
            if (banco.getIdBanco() == idBanco)
                return banco;
        }

        return null;
    }
}
