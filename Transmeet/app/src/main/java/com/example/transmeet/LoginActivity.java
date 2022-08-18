package com.example.transmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText EmailAddress,Password;
    Button login, signup;

    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailAddress = findViewById(R.id.editTextTextEmailAddress);
        Password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.createbtn);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");

//        EmailAddress.addTextChangedListener(textWatcher);
//        Password.addTextChangedListener(textWatcher);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                String email, password;
                email = EmailAddress.getText().toString();
                password = Password.getText().toString();

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(LoginActivity.this, Dashboard.class));
//                            Toast.makeText(LoginActivity.this,"logged in", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }
//    private TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//        String text1 = EmailAddress.getText().toString().trim();
//        String text2 = Password.getText().toString().trim();
//        login.setEnabled((!text1.isEmpty() && !text2.isEmpty() ));
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };
}