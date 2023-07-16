package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class  login extends AppCompatActivity {
    EditText ed1, ed2;
    Button bt1, bt2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ed1 = findViewById(R.id.Edt1);
        ed2 = findViewById(R.id.Edt2);

        bt1 = findViewById(R.id.button1);
        mAuth = FirebaseAuth.getInstance();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = ed1.getText().toString().trim();
                String password = ed2.getText().toString().trim();
                    if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                        ed1.setError("Textfield is empty");}


//                    FragmentManager fm = getSupportFragmentManager();
//                    Bookdetail bd = new Bookdetail();
//                    fm.beginTransaction().add(R.id.Bookdetail, bd).addToBackStack(null).commit();


                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(login.this, PostBook.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });


        bt2 = findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
/*
                bt2.setVisibility(View.GONE);
                FragmentManager fm = getSupportFragmentManager();
                //fragment object to which u want to see
                regst fragment = new regst();
                //linear1 is the id of given xml file
                fm.beginTransaction().add(R.id.linear1, fragment).addToBackStack(null).commit();
*/
            }
        });

    }
}








