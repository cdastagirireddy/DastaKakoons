package com.kuncham.kakoons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchProduct(View view) {

        EditText et=(EditText)findViewById(R.id.search_edit);
        String s=et.getText().toString();
        Intent it=new Intent();
        it.putExtra("q",s);
        setResult(RESULT_OK,it);
        finish();
    }
}
