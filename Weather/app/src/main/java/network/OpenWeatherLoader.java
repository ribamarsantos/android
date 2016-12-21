package network;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import dao.CityRepository;
import model.City;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class OpenWeatherLoader extends AsyncTaskLoader<List<City>> {

    private LatLng coords;
    private boolean isFavoriteList;
    private List<City> cityWeatherList;

    public OpenWeatherLoader(Context context, LatLng coords, boolean isFavoriteList) {
        super(context);
        this.coords  = coords;
        this.isFavoriteList = isFavoriteList;
    }

    @Override
    public List<City> loadInBackground() {
        if (!isFavoriteList){
            cityWeatherList = new OpenWeatherHTTP().getCitiesNearCoordinate(coords);
        }else{
            CityRepository repository = new CityRepository(super.getContext());
            cityWeatherList =repository.listFavorite();
        }

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
