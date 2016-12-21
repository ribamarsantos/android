package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class Weather implements Parcelable {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    private String linkIcon;

    public Weather(){

    }
    protected Weather(Parcel in) {
        description = in.readString();
        icon = in.readString();
        linkIcon = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(icon);
        dest.writeString(linkIcon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public String getLinkIcon() {
        return  "http://openweathermap.org/img/w/" +
                this.icon + ".png";
    }

    public void setLinkIcon(String icon) {
        this.linkIcon = "http://openweathermap.org/img/w/" +
                icon + ".png";
    }
    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
