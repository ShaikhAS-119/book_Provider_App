package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class  historypost extends AppCompatActivity {

    LottieAnimationView anim;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historypost);

        anim=findViewById(R.id.completeCheck);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                anim.setVisibility(View.VISIBLE);
                anim.playAnimation();



            }
        }, 2000);
//        anim.pauseAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getSupportFragmentManager().beginTransaction().add(R.id.container,new Bookdetail()).commit();
//                final Intent mainIntent = new Intent(historypost.this, Bookdetail.class);
//                historypost.this.startActivity(mainIntent);
//                historypost.this.finish();
            }
        }, 5000);






    }
}
