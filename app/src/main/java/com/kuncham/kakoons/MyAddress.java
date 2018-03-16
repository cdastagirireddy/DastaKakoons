package com.kuncham.kakoons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MyAddress extends AppCompatActivity {

    Bundle bn;
    List<Billing> billing;

    TextView tfname,tlname,tcname,tctry,tst1,tst2,tt,ts,tpc,tpe,tea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        tfname=(TextView)findViewById(R.id.first);
        tlname=(TextView)findViewById(R.id.last);
        tcname=(TextView)findViewById(R.id.company);
        tctry=(TextView)findViewById(R.id.country);
        tst1=(TextView)findViewById(R.id.street1);
        tst2=(TextView)findViewById(R.id.apartment);
        tt=(TextView)findViewById(R.id.city);
        ts=(TextView)findViewById(R.id.state);
        tpc=(TextView)findViewById(R.id.postcode);
        tpe=(TextView)findViewById(R.id.mobile);

    }

    public void billingEdit(View view) {
        Intent it=new Intent(this,BillingAddress.class);
        startActivityForResult(it,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK)
        {
            bn=data.getExtras();

            String fname=bn.getString("firstName");
            String lname=bn.getString("lastName");
            String cname=bn.getString("companyName");
            String ctry=bn.getString("country");
            String st1=bn.getString("streetAddress");
            String st2=bn.getString("streetApartment");
            String t=bn.getString("town");
            String s=bn.getString("state");
            String pc=bn.getString("postcode");
            String pe=bn.getString("phone");
            String ea=bn.getString("email");


            tfname.setText(fname);
            tlname.setText(lname);
            tcname.setText(cname);
            tctry.setText(ctry);
            tst1.setText(st1);
            tst2.setText(st2);
            tt.setText(t);
            ts.setText(s);
            tpc.setText(pc);
            tpe.setText(pe);
           // tea.setText(ea);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
