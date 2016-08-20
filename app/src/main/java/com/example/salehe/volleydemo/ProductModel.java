package com.example.salehe.volleydemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Salehe on 8/20/2016.
 */
public class ProductModel {

    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public String price;

    @SerializedName("status")
    public String status;
}
