package carlos.gofinance.interfaces;

import android.provider.BaseColumns;

/**
 * Created by Carlos on 08/06/2017.
 */

public interface Colunas extends BaseColumns {
    //CONTA
    String ID_CONTA = "idCon";
    String VALOR_CONTA = "valCon";
    String TIPO_CONTA = "tipCon";
    String COR_CONTA = "corCon";
    String NOME_BANCO = "nomBan";

    //CARTAO
    String ID_CARTAO = "idCar";
    String VALOR_CARTAO = "valCar";
    String TIPO_CARTAO = "tipCar";
    String COR_CARTAO= "corCar";
    String BANDEIRA_CARTAO = "banCar";

    //DESPESA
    String ID_DESPESA = "idDes";
    String VALOR_DESPESA = "valDes";
    String DATA_DESPESA = "datDes";
    String TIPO_DESPESA = "tipDes";
    String DESPESA_PAGA = "desPag";
    String TITULO_DESPESA = "titDes";

    //RECEITA
    String ID_RECEITA = "idRec";
    String VALOR_RECEITA = "valRec";
    String DATA_RECEITA = "datRec";
    String FONTE_RECEITA = "fonRec";
    String TITULO_RECEITA = "titRec";
}
