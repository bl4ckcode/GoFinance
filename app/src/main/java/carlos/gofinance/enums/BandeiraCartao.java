package carlos.gofinance.enums;

/**
 * Created by Carlos on 10/06/2017.
 */

public enum BandeiraCartao {
    VISA(0),
    MASTERCARD(1),
    AMERICA_EXPRESS(2),
    ELO(3),
    SODEXO(4),
    TICKET_ALIMENTACAO(5),
    OUTRO(6);

    private int idBandeiraCartao;

    BandeiraCartao(int idBandeiraCartao) {
        this.idBandeiraCartao = idBandeiraCartao;
    }

    public int getIdBandeiraCartao() {
        return idBandeiraCartao;
    }

    public static BandeiraCartao valueOf(int idBandeiraCartao) {
        for (BandeiraCartao bandeiraCartao : BandeiraCartao.values()) {
            if (bandeiraCartao.getIdBandeiraCartao() == idBandeiraCartao)
                return bandeiraCartao;
        }

        return null;
    }
}
