package com.example.salehe.volleydemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Salehe on 8/22/2016.
 */
public class Product implements Parcelable{
    private String name;
    private String price;
    private String status;
    public Product(){

    }
    public Product(String name, String price, String status){
        this.name = name;
        this.price =price;
        this.status = status;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readString();
        status = in.readString();

        Log.d("sah","read from parcel");
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            Log.d("sah","create fro parcel");
            return new Product(in);
        }
        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(status);
        Log.d("sah", "write to parcel");
    }
}
