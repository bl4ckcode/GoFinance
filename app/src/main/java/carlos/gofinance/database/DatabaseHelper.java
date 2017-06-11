package carlos.gofinance.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import carlos.gofinance.interfaces.Constantes;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context contexto) {
        super(contexto, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Conta(" +
                "idCon INTEGER NOT NULL, " +
                "valCon DECIMAL(13,4) NOT NULL, " +
                "tipCon INTEGER NOT NULL, " +
                "corCon INTEGER NOT NULL, " +
                "nomBan varchar(30) NOT NULL," +
                "CONSTRAINT PK_Conta PRIMARY KEY (idCon));");

        db.execSQL("CREATE TABLE Cartao(" +
                "idCar INTEGER NOT NULL, " +
                "valCar DECIMAL(13,4) NOT NULL, " +
                "tipCar INTEGER NOT NULL, " +
                "corCar INTEGER NOT NULL, " +
                "banCar varchar(30) NOT NULL," +
                "CONSTRAINT PK_Cartao PRIMARY KEY (idCar));");

        db.execSQL("CREATE TABLE Despesa(" +
                "idDes INTEGER NOT NULL, " +
                "valDes DECIMAL(13,4) NOT NULL, " +
                "datDes DATE NOT NULL, " +
                "tipDes INTEGER NOT NULL," +
                "desPag CHAR(1) NOT NULL, " +
                "titDes varchar(30) NOT NULL," +
                "idCon INTEGER, " +
                "idCar INTEGER, " +
                "CONSTRAINT PK_Despesa PRIMARY KEY(idDes), " +
                "CONSTRAINT FK_Despesa_Conta FOREIGN KEY (idCon) REFERENCES Conta(idCon), " +
                "CONSTRAINT FK_Despesa_Cartao FOREIGN KEY (idCar) REFERENCES Cartao(idCar));");

        db.execSQL("CREATE TABLE Receita(" +
                "idRec INTEGER NOT NULL, " +
                "valRec DECIMAL(13,4) NOT NULL, " +
                "datRec DATE NOT NULL, " +
                "fonRec INTEGER NOT NULL, " +
                "titRec varchar(30) NOT NULL, " +
                "idCon INTEGER, " +
                "idCar INTEGER, " +
                "CONSTRAINT PK_Receita PRIMARY KEY(idRec), " +
                "CONSTRAINT FK_Receita_Conta FOREIGN KEY (idCon) REFERENCES Conta(idCon), " +
                "CONSTRAINT FK_Receita_Cartao FOREIGN KEY (idCar) REFERENCES Cartao(idCar));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
