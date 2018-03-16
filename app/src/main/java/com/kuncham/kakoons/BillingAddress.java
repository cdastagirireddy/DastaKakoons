package com.kuncham.kakoons;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BillingAddress extends AppCompatActivity {


    EditText first_name,last_name,company_name;
    EditText country,street1,street2,town,state,postcode,phone,email;
    Bundle bn;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_address);

        first_name=(EditText)findViewById(R.id.first_name_edit);
        last_name=(EditText)findViewById(R.id.last_name_edit);
        company_name=(EditText)findViewById(R.id.company_name_edit);
        country=(EditText)findViewById(R.id.country_edit);
        street1=(EditText)findViewById(R.id.street_address_edit);
        street2=(EditText)findViewById(R.id.street_address2_edit);
        town=(EditText)findViewById(R.id.town_city_edit);
        state=(EditText)findViewById(R.id.state_country_edit);
        postcode=(EditText)findViewById(R.id.postelcode_edit);
        phone=(EditText)findViewById(R.id.phone_edit);
        email=(EditText)findViewById(R.id.email_address_edit);

        Bundle bn=getIntent().getExtras();

        if (bn!=null && !(bn.isEmpty()))
        {
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

            pos=bn.getInt("position");

            first_name.setText(fname);
            last_name.setText(lname);
            company_name.setText(cname);
            country.setText(ctry);
            street1.setText(st1);
            street2.setText(st2);
            town.setText(t);
            state.setText(s);
            postcode.setText(pc);
            phone.setText(pe);
            email.setText(ea);

        }
    }


    public void saveBillingAddress(View view) {
        String s1=first_name.getText().toString();
        String s2=last_name.getText().toString();
        String s3=company_name.getText().toString();
        String s4=country.getText().toString();
        String s5=street1.getText().toString();
        String s6=street2.getText().toString();
        String s7=town.getText().toString();
        String s8=state.getText().toString();
        String s9=postcode.getText().toString();
        String s10=phone.getText().toString();
        String s11=email.getText().toString();

        Intent it1=new Intent();

        it1.putExtra("firstName",s1);
        it1.putExtra("lastName",s2);
        it1.putExtra("companyName",s3);
        it1.putExtra("country",s4);
        it1.putExtra("streetAddress",s5);
        it1.putExtra("streetApartment",s6);
        it1.putExtra("town",s7);
        it1.putExtra("state",s8);
        it1.putExtra("postcode",s9);
        it1.putExtra("phone",s10);
        it1.putExtra("email",s11);

        setResult(RESULT_OK,it1);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK)
        {
            bn=data.getExtras();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
