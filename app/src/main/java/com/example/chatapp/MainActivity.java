package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView tv1,tv2;
    Button btn1,btn2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.email);
        tv2 = findViewById(R.id.pswd);

        btn1 = findViewById(R.id.btnlogin);
        btn2 = findViewById(R.id.btnreg);
        fAuth = FirebaseAuth.getInstance();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, Registration.class);
                startActivity(i1);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = tv1.getText().toString().trim();
                String pw = tv2.getText().toString().trim();

                if(TextUtils.isEmpty(un)){
                    tv1.setError("Enter E-Mail");
                    return;
                }
                if(TextUtils.isEmpty(pw)) {
                    tv2.setError("Enter Password");
                    return;
                }
                if(pw.length()<6){
                    tv2.setError("Length should be greater than 6");
                    return;
                }

                fAuth.signInWithEmailAndPassword(un,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Successfully!!",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,MainApp.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error Occured!!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}