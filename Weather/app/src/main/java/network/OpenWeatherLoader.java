package network;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import model.City;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class OpenWeatherLoader extends AsyncTaskLoader<List<City>> {

    private LatLng coords;
    private List<City> cityWeatherList;

    public OpenWeatherLoader(Context context, LatLng coords) {
        super(context);
        this.coords  = coords;
    }

    @Override
    public List<City> loadInBackground() {
        cityWeatherList = new OpenWeatherHTTP(coords.latitude, coords.longitude).getCitiesWeather();
        return  cityWeatherList;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if ( cityWeatherList == null){
            forceLoad();
        }else{
            deliverResult(cityWeatherList);
        }
    }

    @Override
    public void deliverResult(List<City> data) {
        super.deliverResult(data);
    }
}
