package com.example.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class ELoldbook extends AppCompatActivity implements PaymentResultListener {
    ImageView imageView,imageView2;
    TextView price,page,bookName,writerName,publisherName,contact;
    Button  button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_loldbook);

        imageView= findViewById(R.id.imageView);
        imageView2= findViewById(R.id.imageView1);
        price= findViewById(R.id.textView);
        page= findViewById(R.id.textView1);
        bookName=findViewById(R.id.textView2);
        writerName=findViewById(R.id.textView3);
        publisherName=findViewById(R.id.textView4);
        contact=findViewById(R.id.textView5);

        price.setText(getIntent().getStringExtra("price"));
        page.setText(getIntent().getStringExtra("pages"));
        bookName.setText(getIntent().getStringExtra("bookname"));
        writerName.setText(getIntent().getStringExtra("writer"));
        publisherName.setText(getIntent().getStringExtra("publisher"));
        contact.setText(getIntent().getStringExtra("contact"));
        Glide.with(this).load(getIntent().getStringExtra("Url")).into(imageView);

        imageView2.setOnClickListener(v -> {
            String con= contact.getText().toString();

//            Intent intent=new Intent(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse("tel:"+con));
//            startActivity(intent);

            Uri u = Uri.parse("tel:"+con);
            Intent i = new Intent(Intent.ACTION_DIAL, u);
            startActivity(i);

        });

        button=findViewById(R.id.button);
        button.setOnClickListener(view -> {
            //String samount = amountEdt.getText().toString();
            String samount=getIntent().getStringExtra("price");

            // rounding off the amount.
             int amount = Math.round(Float.parseFloat(samount) * 100);

            // initialize Razorpay account.
            Checkout checkout = new Checkout();

            // set your id as below
            checkout.setKeyID("rzp_test_nU1zAFCbLOQllE");

            // set image
            //checkout.setImage(R.drawable.gfgimage);

            // initialize json object
            JSONObject object = new JSONObject();
            try {
                // to put name
               // object.put("name", "Geeks for Geeks");

                // put description
                object.put("description", " payment");

                // to set theme color
               // object.put("theme.color", "");

                // put the currency
                object.put("currency", "INR");

                // put amount
                object.put("amount", amount );

                // put mobile number
               // object.put("prefill.contact", "9284064503");

                // put email
               // object.put("prefill.email", "chaitanyamunje@gmail.com");

                // open razorpay to checkout activity
                checkout.open(ELoldbook.this, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(ELoldbook.this, "success", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(ELoldbook.this, "try again", Toast.LENGTH_SHORT).show();
    }
}