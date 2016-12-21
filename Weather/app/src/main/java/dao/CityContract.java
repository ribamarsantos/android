package dao;


import android.provider.BaseColumns;

/**
 * Created by ribamarmjs on 20/12/16.
 */

public interface  CityContract extends BaseColumns {
    String TB_CITY = "City";

    String COL_ID_WEB = "idWeb";
    String COL_NAME   = "name";
    String COL_TEMP_MAX = "temp_max";
    String COL_TEMP_MIN = "temp_min";
    String COL_WEATHER_DESCRIPTION = "weather_description";
    String COL_URL_ICON = "url_icon";
    String COL_CREATED = "created";

    String [] COLUMNS = new String[]{
            CityContract._ID,
            CityContract.COL_ID_WEB,
            CityContract.COL_NAME,
            CityContract.COL_TEMP_MAX,
            CityContract.COL_TEMP_MIN,
            CityContract.COL_WEATHER_DESCRIPTION,
            CityContract.COL_URL_ICON,
            CityContract.COL_CREATED
    };


}
