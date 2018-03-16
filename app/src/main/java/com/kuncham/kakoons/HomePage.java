package com.kuncham.kakoons;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    List<Product> details;
    ProductAdapter pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        details=new Vector<Product>();
        lv=(ListView)findViewById(R.id.lv1);
        pa = new ProductAdapter(this,details,R.layout.home_list_product);
        lv.setAdapter(pa);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                Intent it=new Intent(HomePage.this,SearchActivity.class);
                startActivityForResult(it,1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        load(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                String s=data.getStringExtra("q");
                load(s);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==R.id.action_logout){

            AlertDialog.Builder adb=new AlertDialog.Builder(HomePage.this);
            adb.setMessage("Do you want Logout?");
            adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            adb.create().show();
            return true;
        }
        if (id == R.id.action_faq){

        }
        if (id == R.id.action_cart){

        }
        if (id == R.id.feedback){

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_shop) {

        } else if (id == R.id.nav_fashions) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_profile){
            Intent it=new Intent(this,MyProfile.class);
            startActivity(it);

        } else if (id == R.id.nav_passwd){
            Intent it=new Intent(this,ChangePassword.class);
            startActivity(it);

        } else if (id == R.id.nav_orders){
            Intent it=new Intent(this,MyOrders.class);
            startActivity(it);

        } else if (id == R.id.nav_address){
            Intent it=new Intent(this,MyAddress.class);
            startActivity(it);

        } else if (id == R.id.nav_wishlist){
            Intent it=new Intent(this,MyWishlist.class);
            startActivity(it);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void load(String s)
    {
        if(s==null)
            s="london";
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
                                catch (Exception e)
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

                        Toast.makeText(HomePage.this, "Try again later", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsObjRequest);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(HomePage.this,ProductCategories.class);
                it.putExtra("p",(Serializable)pa.getItem(i));
                startActivity(it);
            }
        });

    }


}
