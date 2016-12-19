package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class City implements Parcelable {

    private int id;

    private String name;

    @SerializedName("main")
    @Expose
    private Temperature temperature;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;


    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        temperature = in.readParcelable(Temperature.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeParcelable(temperature, flags);
        dest.writeTypedList(weather);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public List<Weather> getWeather() {
        return weather;
    }

}
