package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class City implements Parcelable {

    private long id;

    private String name;

    @SerializedName("main")
    @Expose
    private Temperature temperature;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;
    private int idBD;

    private String created;

    public City(){
        weather = new ArrayList<>();
    }

    protected City(Parcel in) {
        id = in.readLong();
        name = in.readString();
        temperature = in.readParcelable(Temperature.class.getClassLoader());
        weather = in.createTypedArrayList(Weather.CREATOR);
        idBD = in.readInt();
        created = in.readString();
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

    public long getId() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdBD(int idBD) {
        this.idBD = idBD;
    }

    public int getIdBD() {
        return idBD;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeParcelable(temperature, i);
        parcel.writeTypedList(weather);
        parcel.writeInt(idBD);
        parcel.writeString(created);
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }


    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }
}
