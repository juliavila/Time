package juliavila.time;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by julia.vila on 22/02/2016.
 */
public class PontoRepo {
    private DBHelper dbHelper;

    public PontoRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Ponto Ponto) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ponto.KEY_dataInicial, Ponto.dataInicial.toString());
        values.put(Ponto.KEY_dataFinal,Ponto.dataFinal.toString());

        // Inserting Row
        long Ponto_Id = db.insert(Ponto.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) Ponto_Id;
    }

    public void delete(int Ponto_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Ponto.TABLE, Ponto.KEY_ID + "= ?", new String[]{String.valueOf(Ponto_Id)});
        db.close(); // Closing database connection
    }

    public void update(Ponto Ponto) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Ponto.KEY_dataInicial, Ponto.dataInicial.toString());
        values.put(Ponto.KEY_dataFinal, Ponto.dataFinal.toString());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Ponto.TABLE, values, Ponto.KEY_ID + "= ?", new String[]{String.valueOf(Ponto.ponto_ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getPontoList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Ponto.KEY_ID + "," +
                Ponto.KEY_dataInicial + "," +
                Ponto.KEY_dataFinal +
                " FROM " + Ponto.TABLE;

        //Ponto Ponto = new Ponto();
        ArrayList<HashMap<String, String>> PontoList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> Ponto = new HashMap<String, String>();
                Ponto.put("id", cursor.getString(cursor.getColumnIndex(juliavila.time.Ponto.KEY_ID)));
                PontoList.add(Ponto);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return PontoList;

    }

    public Ponto getPontoById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Ponto.KEY_ID + "," +
                Ponto.KEY_dataInicial + "," +
                Ponto.KEY_dataFinal +
                " FROM " + Ponto.TABLE
                + " WHERE " +
                Ponto.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Ponto Ponto = new Ponto();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                Ponto.ponto_ID = cursor.getInt(cursor.getColumnIndex(Ponto.KEY_ID));
                String dataInicial = cursor.getString(cursor.getColumnIndex(Ponto.KEY_dataInicial));
                String dataFinal = cursor.getString(cursor.getColumnIndex(Ponto.KEY_dataFinal));

                try {
                    Ponto.dataInicial = stringToDate(dataInicial);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    Ponto.dataFinal = stringToDate(dataFinal);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Ponto;
    }

    private Date stringToDate(String dataStr) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dataHora = null;
        try {
            dataHora = (Date) format.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataHora;
    }
}
