package model;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

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
        return "http://openweathermap.org/img/w/" +
                this.icon + ".png";
    }

    public void setLinkIcon(String linkIcon) {
        this.linkIcon = "http://openweathermap.org/img/w/" +
                this.icon + ".png";
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }



}
