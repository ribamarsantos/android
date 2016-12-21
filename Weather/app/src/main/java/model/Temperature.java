package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class Temperature implements Parcelable {

    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;

    public Temperature() {
    }

    protected Temperature(Parcel in) {
        tempMin = in.readDouble();
        tempMax = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(tempMin);
        dest.writeDouble(tempMax);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Temperature> CREATOR = new Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
