package carlos.gofinance.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import carlos.gofinance.database.DatabaseHelper;
import carlos.gofinance.interfaces.Colunas;
import carlos.gofinance.interfaces.Constantes;
import carlos.gofinance.models.Cartao;
import carlos.gofinance.models.Conta;
import carlos.gofinance.models.Despesa;
import carlos.gofinance.models.Receita;

public class Utility {
    public static void insert(String tableName, ContentValues contentValues, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("INSERT OPERATION CODE: ", "" + database.insertOrThrow(tableName, null, contentValues));
        database.close();
    }

    public static void remove(String whereClause, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Log.d("DELETE OPERATION CODE: ", "" + database.delete(Constantes.DATABASE_NAME, whereClause, null));
        database.close();
    }

    public static ArrayList<Conta> obterContas(Context context) {
        ArrayList<Conta> contas = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_CONTA, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            final int tipoConta = cursor.getColumnIndex(Colunas.TIPO_CONTA);
            if (tipoConta != -1)
                contas.add(new Conta(cursor.getInt(cursor.getColumnIndex(Colunas.ID_CONTA)),
                        cursor.getDouble(cursor.getColumnIndex(Colunas.VALOR_CONTA)),
                        tipoConta,
                        cursor.getInt(cursor.getColumnIndex(Colunas.COR_CONTA)),
                        cursor.getString(cursor.getColumnIndex(Colunas.NOME_BANCO))));
        }
        cursor.close();
        database.close();
        return contas;
    }

    public static ArrayList<Cartao> obterCartoes(Context context) {
        ArrayList<Cartao> cartoes = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_CARTAO, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            final int tipoCartao = cursor.getColumnIndex(Colunas.TIPO_CARTAO);
            if (tipoCartao != -1)
                cartoes.add(new Cartao(cursor.getInt(cursor.getColumnIndex(Colunas.ID_CARTAO)),
                        cursor.getDouble(cursor.getColumnIndex(Colunas.VALOR_CARTAO)),
                        tipoCartao,
                        cursor.getInt(cursor.getColumnIndex(Colunas.COR_CARTAO)),
                        cursor.getString(cursor.getColumnIndex(Colunas.BANDEIRA_CARTAO))));
        }
        cursor.close();
        database.close();
        return cartoes;
    }

    public static ArrayList<Despesa> obterDespesas(Context context) {
        ArrayList<Despesa> despesas = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_DESPESA, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            boolean isConta = cursor.isNull(cursor.getColumnIndex(Colunas.ID_CONTA));
            boolean isCartao = !isConta;
            despesas.add(new Despesa(cursor.getInt(cursor.getColumnIndex(Colunas.ID_DESPESA)),
                    cursor.getDouble(cursor.getColumnIndex(Colunas.VALOR_DESPESA)),
                    cursor.getString(cursor.getColumnIndex(Colunas.DATA_DESPESA)),
                    cursor.getInt(cursor.getColumnIndex(Colunas.TIPO_DESPESA)),
                    cursor.getString(cursor.getColumnIndex(Colunas.DESPESA_PAGA)).charAt(0),
                    cursor.getString(cursor.getColumnIndex(Colunas.TITULO_DESPESA)),
                    isConta ? cursor.getInt(cursor.getColumnIndex(Colunas.ID_CONTA)) : -1,
                    isCartao ? cursor.getInt(cursor.getColumnIndex(Colunas.ID_CARTAO)) : -1));
        }
        cursor.close();
        database.close();
        return despesas;
    }

    public static ArrayList<Receita> obterReceitas(Context context) {
        ArrayList<Receita> receitas = new ArrayList<>();

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constantes.TABLE_RECEITA, null, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            boolean isConta = cursor.isNull(cursor.getColumnIndex(Colunas.ID_CONTA));
            boolean isCartao = !isConta;
            receitas.add(new Receita(cursor.getInt(cursor.getColumnIndex(Colunas.ID_RECEITA)),
                    cursor.getDouble(cursor.getColumnIndex(Colunas.VALOR_RECEITA)),
                    cursor.getString(cursor.getColumnIndex(Colunas.DATA_RECEITA)),
                    cursor.getInt(cursor.getColumnIndex(Colunas.FONTE_RECEITA)),
                    cursor.getString(cursor.getColumnIndex(Colunas.TITULO_RECEITA)),
                    isConta ? cursor.getInt(cursor.getColumnIndex(Colunas.ID_CONTA)) : -1,
                    isCartao ? cursor.getInt(cursor.getColumnIndex(Colunas.ID_CARTAO)) : -1));
        }
        cursor.close();
        database.close();
        return receitas;
    }
}
