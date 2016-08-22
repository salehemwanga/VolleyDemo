package com.example.salehe.volleydemo.activities;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.salehe.volleydemo.Adapter.MySingleton;
import com.example.salehe.volleydemo.Adapter.ProductAdapter;
import com.example.salehe.volleydemo.R;
import com.example.salehe.volleydemo.extras.Keys;
import com.example.salehe.volleydemo.extras.UrlEndpoint;
import com.example.salehe.volleydemo.pojo.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_PRODUCT = "state_product";
    TextView volleyError;

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleyError = (TextView) findViewById(R.id.volleyError);
        recyclerView = (RecyclerView) findViewById(R.id.listView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        productAdapter = new ProductAdapter(getApplicationContext());
        recyclerView.setAdapter(productAdapter);

        if (savedInstanceState != null) {
            Log.d("sah", "this is not null");
            arrayList = savedInstanceState.getParcelableArrayList(STATE_PRODUCT);
            productAdapter.setProductList(arrayList);
        } else {
            sendJsonRequest();
        }




    }

    public void sendJsonRequest() {


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, UrlEndpoint.URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayList = parseJsonResponse(response);
                productAdapter.setProductList(arrayList);
                Toast.makeText(getApplicationContext(), "data load: " + arrayList.size(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyError.setVisibility(View.VISIBLE);
                volleyError.setText(R.string.time_out);
            }
        });

//        requestQueue.add(request);
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    private ArrayList<Product> parseJsonResponse(JSONArray response) {
        ArrayList<Product> arrayList = new ArrayList<>();
        if (response != null || response.length() > 0) {

            try {
//                StringBuilder data=new StringBuilder();
//                JSONArray arrayProduct = new JSONArray();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject currentMovie = response.getJSONObject(i);
                    String name = currentMovie.getString(Keys.endpointProduct.KEY_NAME);
                    String price = currentMovie.getString(Keys.endpointProduct.KEY_PRICE);
                    String status = currentMovie.getString(Keys.endpointProduct.KEY_STATUS);

                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    product.setStatus(status);

                    arrayList.add(product);

                }

            } catch (JSONException e) {

                e.printStackTrace();
            }

        }/*end if statement*/
        return arrayList;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_PRODUCT, arrayList);
    }

}
