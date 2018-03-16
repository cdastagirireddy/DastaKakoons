package com.kuncham.kakoons;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void uploadImage(View view) {
        AlertDialog.Builder adb=new AlertDialog.Builder(MyProfile.this);
        adb.setMessage("Choose Image");
        adb.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it,1);
            }
        });
        adb.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent it=new Intent(Intent.ACTION_PICK);
                it.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(it,2);
            }
        });
        adb.create().show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView iv = (ImageView) findViewById(R.id.profile_image);

        if(requestCode==1) {
            Bundle bn = data.getExtras();
            Bitmap bm = (Bitmap) bn.get("data");
            iv.setImageBitmap(bm);
        }
        else
        if(requestCode==2)
        {
            Uri uri=data.getData();

            iv.setImageURI(uri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
