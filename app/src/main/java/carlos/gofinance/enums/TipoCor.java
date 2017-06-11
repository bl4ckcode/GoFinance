package carlos.gofinance.enums;

import carlos.gofinance.R;

/**
 * Created by Carlos on 10/06/2017.
 */
public enum TipoCor {
    ROXO(0, R.color.purple),
    VERMELEHO(1, R.color.red),
    AZUL(2, R.color.blue),
    ROSA(3, R.color.pink),
    AMARELO(4, R.color.yellow),
    MARROM(5, R.color.brown),
    PRETO(6, R.color.black),
    CINZA(7, R.color.grey),
    VERDE(8, R.color.green);

    private int idCor, resIdColor;

    TipoCor(int idCor, int resIdColor) {
        this.idCor = idCor;
        this.resIdColor = resIdColor;
    }

    public int getIdCor() {
        return idCor;
    }

    public int getResIdColor() {
        return resIdColor;
    }

    public static TipoCor valueOf(int idCor) {
        for(TipoCor cor : TipoCor.values()) {
            if(cor.getIdCor() == idCor)
                return cor;
        }

        return null;
    }
}
