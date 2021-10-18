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
import com.google.firebase.auth.FirebaseAuthException;

public class Registration extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    Button btn1,btn2;
    String un,pw,confirm;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tv1 = findViewById(R.id.confirmmail);
        tv2 = findViewById(R.id.confirmpswd);
        tv3 = findViewById(R.id.password);

        btn2 = findViewById(R.id.btnregistration);
        fAuth = FirebaseAuth.getInstance();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un = tv1.getText().toString().trim();
                pw = tv2.getText().toString().trim();
                confirm = tv3.getText().toString().trim();

                if(TextUtils.isEmpty(un)){
                    tv1.setError("Enter E-Mail");
                    return;
                }
                if(TextUtils.isEmpty(pw)){
                    tv2.setError("Enter Password");
                    return;
                }
                if(pw.length()<6){
                    tv2.setError("Length should be greater than 6");
                    return;
                }
                if(un!=pw){
                    tv3.setError("Password not match!!");
                }
                fAuth.createUserWithEmailAndPassword(un, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Registered Successfully!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Registration.this, MainActivity.class));
                        } else {
                            Toast.makeText(Registration.this, "Error Occured!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}