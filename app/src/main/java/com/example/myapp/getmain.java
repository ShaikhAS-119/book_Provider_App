package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

public class getmain extends AppCompatActivity {
    CardView cardView1, cardView2, cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getmain);


        cardView1 = findViewById(R.id.card1);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                Intent intent = new Intent(getmain.this,oldBook.class);
                startActivity(intent);
                //finish();
                //FragmentManager fm = getSupportFragmentManager();
                //oldbook fragment = new oldbook();
                //fm.beginTransaction().replace(R.id.get,fragment).addToBackStack(null).show(fragment).commit();
                //ho sake to use back intent activity
            }
        });

                //card2
                cardView2 = findViewById(R.id.card2);
                cardView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardView1.setVisibility(View.GONE);
                        cardView2.setVisibility(View.GONE);
                        cardView3.setVisibility(View.GONE);
                        FragmentManager fm = getSupportFragmentManager();
                        newbook frag = new newbook();
                        fm.beginTransaction().replace(R.id.get,frag).addToBackStack(null).commit();
                    }

                });

                        //card3
                        cardView3 = findViewById(R.id.card3);
                        cardView3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                cardView1.setVisibility(View.GONE);
                                cardView2.setVisibility(View.GONE);
                                cardView3.setVisibility(View.GONE);
                                 Intent intent=new Intent(getmain.this,pdf.class);
                                 startActivity(intent);

                            }
                        });

    }
}