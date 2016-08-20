package com.example.salehe.volleydemo;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Response.Listener<String> {

    final String TAG = this.getClass().getSimpleName();

    TextView name,status,price;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView) findViewById(R.id.tvName);
        status = (TextView) findViewById(R.id.tvStatus);
        price = (TextView) findViewById(R.id.tvPrice);
        listView = (ListView) findViewById(R.id.listView);
        fetchData();
    }

    public void fetchData(){
        String url="http://10.0.3.2/androidVolleyTest/test.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error while reading data", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onResponse(String response) {
        Log.d("Sah", response);
        ArrayList<ProductModel> arrayList = new JsonConverter<ProductModel>().toArrayList(response,ProductModel.class);
        BindDictionary<ProductModel> dict = new BindDictionary<ProductModel>();
        dict.addStringField(R.id.tvName,
                new StringExtractor<ProductModel>() {
                    @Override
                    public String getStringValue(ProductModel item, int position) {
                        return item.name;
                    }
                });
        dict.addStringField(R.id.tvPrice,
                new StringExtractor<ProductModel>() {
                    @Override
                    public String getStringValue(ProductModel item, int position) {
                        return item.price;
                    }
                });
        dict.addStringField(R.id.tvStatus,
                new StringExtractor<ProductModel>() {
                    @Override
                    public String getStringValue(ProductModel item, int position) {
                        return item.status;
                    }
                });

        FunDapter<ProductModel> adapter = new FunDapter<ProductModel>(getApplicationContext(), arrayList,R.layout.single_row, dict);
        listView.setAdapter(adapter);

    }
}
