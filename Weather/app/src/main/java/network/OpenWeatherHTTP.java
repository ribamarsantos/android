package network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.City;
import model.WeatherWrapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class OpenWeatherHTTP {

    private final double longitude;
    private final double latitude;

    //URL
    public static final HttpUrl API_BASE_URL = HttpUrl.parse("http://api.openweathermap.org/data/2.5");
    //PATH
    public static final String PATH_FIND     = "find";

    //PARAMS
    public static final String PARAM_LATITUDE  = "lat";
    public static final String PARAM_LONGITUDE = "lon";
    public static final String PARAM_CNT = "cnt";
    public static final String PARAM_APPID = "APPID";
    public static final String PARAM_UNITS = "units";
    public static final String PARAM_LANG = "lang";

    // VALUES THAT CAN BE CONFIGURABLE
    private static final String LANGUAGE_DESCRIPTION = "pt";
    private static final String UNIT = "metric";
    private static final String APPID = "68988c96250c1d2068f922c26a917810";
    private static final String SIZE_LIST = "15";

    protected OpenWeatherHTTP(double latitude, double longitude){
        this.latitude  = latitude;
        this.longitude = longitude;
    }
    public  ArrayList<City> getCitiesWeather(){

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(getHttpUrl())
                .build();

        Response response;

        ArrayList<City> cities = new ArrayList<>();


        try {
            response = client.newCall(request).execute();

            if ( !response.isSuccessful()) throw new IOException("Erro ao buscar cidades");
            String json = response.body().string();

            Gson gson = new Gson();

            WeatherWrapper dataWrapper = gson.fromJson(json, WeatherWrapper.class);

            cities.addAll(dataWrapper.getCities());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return cities;
    }


    @NonNull
    private HttpUrl getHttpUrl (){
        return API_BASE_URL.newBuilder()
                .addPathSegment(PATH_FIND)
                .addQueryParameter(PARAM_LATITUDE, String.valueOf(latitude))
                .addQueryParameter(PARAM_LONGITUDE, String.valueOf(longitude))
                .addQueryParameter(PARAM_CNT,   SIZE_LIST)
                .addQueryParameter(PARAM_APPID, APPID)
                .addQueryParameter(PARAM_UNITS, UNIT)
                .addQueryParameter(PARAM_LANG,  LANGUAGE_DESCRIPTION)
                .build();
    }
}
