package com.example.phonebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PersonAdapter personAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Person>personArrayList;
    ArrayList<String>names;
    AutoCompleteTextView autoCompleteTextView;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personArrayList=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        names=new ArrayList<>();
        initData();
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.person_rec);
        recyclerView.setLayoutManager(linearLayoutManager);


        autoCompleteTextView=findViewById(R.id.autoComplete);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(2);

    }

    private void initData() {
    String url="https://phone-book980-yv.herokuapp.com/api/contacts";

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               for(int i=0;i<response.length();i++)
               {
                   try {
                       JSONObject jsonObject=response.getJSONObject(i);
                       JSONArray phoneNumbers=jsonObject.getJSONArray("phoneNumbers");
                        Long phone= (Long) phoneNumbers.get(0);
                        String phoneNum=phone+"";
                        JSONArray emails=jsonObject.getJSONArray("emails");
                        String email= (String) emails.get(0);
                        String dob=jsonObject.getString("dob");
                        String name=jsonObject.getString("name");
                        personArrayList.add(new Person(name,email,phoneNum,dob));
                        names.add(name);
                   } catch (JSONException e) {
                       Log.d("Exception ", e.getMessage());
                       e.printStackTrace();
                   }

               }
                personAdapter=new PersonAdapter(MainActivity.this,personArrayList);
                recyclerView.setAdapter(personAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       requestQueue.add(jsonArrayRequest);
    }
}