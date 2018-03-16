package com.kuncham.kakoons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class ProductCategories extends AppCompatActivity {

    GridView gridview;
    ProductAdapter pa;
    List<Product> details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_categories);

        details=new Vector<Product>();
        gridview=(GridView)findViewById(R.id.grid_view);
        pa=new ProductAdapter(this,details,R.layout.home_grid_list);
        gridview.setAdapter(pa);
        load(getIntent().getStringExtra("q"));
    }

    public void load(String s)
    {
        String url = "http://api.geonames.org/wikipediaSearchJSON?formatted=true&q="+s+"&maxRows=10&username=srik784&style=full";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        details.clear();

                        try {
                            JSONArray ja=response.getJSONArray("geonames");
                            for(int i=0;i<ja.length();i++)
                            {
                                try {
                                    JSONObject jas = ja.getJSONObject(i);


                                    Product p = new Product();
                                    p.setName(jas.getString("title"));
                                    p.setDescription(jas.getString("summary"));
                                    p.setImage_url(jas.getString("thumbnailImg"));


                                    details.add(p);
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        pa.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                        Toast.makeText(ProductCategories.this, "Try again later", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent it=new Intent(ProductCategories.this,ProductOverview.class);
                it.putExtra("p",(Serializable)pa.getItem(position));
                startActivity(it);

            }
        });



    }
}
