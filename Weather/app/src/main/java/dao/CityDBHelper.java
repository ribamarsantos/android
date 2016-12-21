package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ribamarmjs on 20/12/16.
 */

public class CityDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbCity";

    public CityDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder createTableCharacter = new StringBuilder();

        db.execSQL(createTableCharacter
                .append("CREATE TABLE " + CityContract.TB_CITY + "( ")
                .append(  CityContract._ID             + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(  CityContract.COL_ID_WEB      + " INTEGER  NOT NULL,")
                .append(  CityContract.COL_NAME        + " TEXT NOT NULL,")
                .append(  CityContract.COL_TEMP_MAX    + " NUMBER NOT NULL,")
                .append(  CityContract.COL_TEMP_MIN    + " NUMBER NOT NULL,")
                .append(  CityContract.COL_WEATHER_DESCRIPTION    + " TEXT NOT NULL,")
                .append(  CityContract.COL_URL_ICON    + " TEXT NOT NULL,")
                .append(  CityContract.COL_CREATED     + " DATETIME DEFAULT CURRENT_TIMESTAMP)")
                .toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
