package com.example.salehe.volleydemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Salehe on 8/20/2016.
 */
public class ProductModel implements Parcelable {
    public String name;
    public String price;
    public String status;

    protected ProductModel(Parcel in) {
        name = in.readString();
        price = in.readString();
        status = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            Log.d("sah","create from parcel :ProductModel");
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d("sah","write to parcel");
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(status);
    }
}
