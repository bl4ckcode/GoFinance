package carlos.gofinance.enums;

/**
 * Created by Carlos on 11/06/2017.
 */

public enum TipoReceita {
    SALARIO(0),
    AUTONOMO(1),
    PRESENTE(2),
    OUTRO(3);

    private int idReceita;

    TipoReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    public int getIdReceita() {
        return idReceita;
    }

    public static TipoReceita valueOf(int idReceita) {
        for (TipoReceita receita : TipoReceita.values()) {
            if (receita.getIdReceita() == idReceita)
                return receita;
        }

        return null;
    }
}
