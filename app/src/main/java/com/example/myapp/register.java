package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class register extends AppCompatActivity {
    EditText Edt2,Edt4,Edt5;
    Button button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        Edt2=findViewById(R.id.Edt2);
        Edt4=findViewById(R.id.Edt4);
        Edt5=findViewById(R.id.Edt5);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
           // @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {


                 String email=Edt2.getText().toString();
                 String password=Edt4.getText().toString();
                 mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),login.class));
                            Toast.makeText(register.this, "success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(register.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                     }
                 });


            }
        });
    }
}