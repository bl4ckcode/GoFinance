package carlos.gofinance.enums;

import carlos.gofinance.R;

/**
 * Created by Carlos on 10/06/2017.
 */
public enum TipoConta {
    CORRENTE(0), POUPANCA(1), SALARIO(2);

    private int idTipoConta;

    TipoConta(int idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public int getIdTipoConta() {
        return idTipoConta;
    }

    public static TipoConta valueOf(int idTipoConta) {
        for (TipoConta conta : TipoConta.values()) {
            if (conta.getIdTipoConta() == idTipoConta)
                return conta;
        }

        return null;
    }
}
