package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class WeatherWrapper {
    @SerializedName("cod")
    @Expose
    private int cod;

    @SerializedName("list")
    @Expose
    private List<City> cities;

    public int getCod() {
        return cod;
    }

    public List<City> getCities() {
        return cities;
    }
}
