package com.example.cryptoguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;

    private ArrayList<Crypto> al;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);
        editText = findViewById(R.id.textView3);
        al = new ArrayList<>();
        customAdapter = new CustomAdapter(al, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
        getData();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
        filter_searching(s.toString());
            }
        });



    }
  private void filter_searching(String curr)
    {
     ArrayList<Crypto> filteredList = new ArrayList<>();
    for(Crypto item:al)
    {
        if(item.getName().toLowerCase().contains(curr.toLowerCase()))
        {
            filteredList.add(item);
        }
    }
    if(filteredList.size()==0)
    {
        Toast.makeText(this, "No such crpto currency found", Toast.LENGTH_SHORT).show();
    }
    else
        customAdapter.filterList(filteredList);
    }
    private void getData() {

        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");

                        String symbol = jsonObject.getString("symbol");
                        JSONObject jsonObject1 = jsonObject.getJSONObject("quote");
                        JSONObject USD = jsonObject1.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        al.add(new Crypto(name, symbol, price));

                    }
                    customAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "Error in finding data ", Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "3c94a554-820c-4f8c-81b3-30a623bc3425");
                return headers;
            }
        };




        requestQueue.add(jsonObjectRequest);

    }
    }