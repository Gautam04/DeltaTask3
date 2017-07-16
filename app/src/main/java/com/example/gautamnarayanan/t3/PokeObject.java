package com.example.gautamnarayanan.t3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gautam Narayanan on 7/16/2017.
 */

public class PokeObject implements Parcelable {
    private String name;
    private  String url;


    public PokeObject(String name,String url) {
        this.name=name;
        this.url=url;
    }

    protected PokeObject(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PokeObject> CREATOR = new Creator<PokeObject>() {
        @Override
        public PokeObject createFromParcel(Parcel in) {
            return new PokeObject(in);
        }

        @Override
        public PokeObject[] newArray(int size) {
            return new PokeObject[size];
        }
    };

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
