package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.Temperature;
import model.Weather;

/**
 * Created by ribamarmjs on 20/12/16.
 */

public class CityRepository {

    CityDBHelper mHelper;

    public CityRepository(Context ctx) {
        mHelper = new CityDBHelper(ctx);
    }


    public void insert(City city) {
        ContentValues values = getValuesFromCity(city);
        SQLiteDatabase db = mHelper.getWritableDatabase();

        long id = db.insertOrThrow(CityContract.TB_CITY, null, values);
        if (id <= 0) throw new RuntimeException("Erro ao inserir!");
        db.close();
    }

    public void delete(City city){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(CityContract.TB_CITY,
                CityContract._ID +" = ?",
                new String[]{ String.valueOf(city.getIdBD())});
        db.close();
    }

    public List<City> listFavorite(){
        List<City> cities = new ArrayList<>();

        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM "+ CityContract.TB_CITY +
                        " ORDER BY "+ CityContract.COL_NAME, null);

        if (cursor.getCount() > 0) {

            int idxId   = cursor.getColumnIndex(CityContract.COL_ID_WEB);
            int idxIdDB = cursor.getColumnIndex(CityContract._ID);
            int idxName = cursor.getColumnIndex(CityContract.COL_NAME);
            int idxTempMax = cursor.getColumnIndex(CityContract.COL_TEMP_MAX);
            int idxTempMin = cursor.getColumnIndex(CityContract.COL_TEMP_MIN);
            int idxWeatherDesc = cursor.getColumnIndex(CityContract.COL_WEATHER_DESCRIPTION);
            int idxCreated     = cursor.getColumnIndex(CityContract.COL_CREATED);
            int idxUrl         = cursor.getColumnIndex(CityContract.COL_URL_ICON);

            while (cursor.moveToNext()){
                City city = new City();
                Weather weather = new Weather();
                Temperature temperature = new Temperature();

                weather.setDescription(cursor.getString(idxWeatherDesc));
                weather.setIcon(cursor.getString(idxUrl));
                weather.setLinkIcon(cursor.getString(idxUrl));
                temperature.setTempMax(cursor.getDouble(idxTempMax));
                temperature.setTempMin(cursor.getDouble(idxTempMin));

                city.setId(cursor.getLong(idxId));
                city.setName(cursor.getString(idxName));
                city.setIdBD(cursor.getInt(idxIdDB));
                city.setTemperature(temperature);
                city.getWeather().add(weather);
                city.setCreated(cursor.getString(idxCreated));
                cities.add(city);
            }
        }
        cursor.close();

        db.close();

        return cities;
    }

    public boolean isFavorite(long idWeb){

        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM "+ CityContract.TB_CITY +
                        " WHERE " + CityContract.COL_ID_WEB  + "= ?", new String[]{ String.valueOf(idWeb)});
        int rows = cursor.getCount();
        cursor.close();
        db.close();
        return rows > 0;
    }

    private ContentValues getValuesFromCity(City city){
        ContentValues cv = new ContentValues();
        cv.put(CityContract.COL_ID_WEB, city.getId());
        cv.put(CityContract.COL_NAME, city.getName());
        cv.put(CityContract.COL_WEATHER_DESCRIPTION, city.getWeather().get(0).getDescription());
        cv.put(CityContract.COL_URL_ICON, city.getWeather().get(0).getIcon());
        cv.put(CityContract.COL_TEMP_MAX, city.getTemperature().getTempMax());
        cv.put(CityContract.COL_TEMP_MIN, city.getTemperature().getTempMin());
        cv.put(CityContract.COL_CREATED, city.getCreated());
        return  cv;
    }
}
