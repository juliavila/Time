package juliavila.time;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julia.vila on 22/02/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "ponto.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_PONTO = "CREATE TABLE " + Ponto.TABLE  + "("
                + Ponto.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Ponto.KEY_dataInicial + " DATETIME, "
                + Ponto.KEY_dataFinal + " DATETIME )";

        db.execSQL(CREATE_TABLE_PONTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Ponto.TABLE);

        // Create tables again
        onCreate(db);

    }
}
