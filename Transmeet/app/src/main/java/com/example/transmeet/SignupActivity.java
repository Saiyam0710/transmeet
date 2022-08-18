package com.example.transmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    EditText name, email, password;
    Button create,already;
    FirebaseAuth auth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        name = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        create = findViewById(R.id.loginbtn2);
        already = findViewById(R.id.createbtn2);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1, pass, name1;
                email1 = email.getText().toString();
                pass = password.getText().toString();
                name1 = name.getText().toString();


                Users users = new Users();
                users.setEmail1(email1);
                users.setPass(pass);
                users.setName1(name1);

                auth.createUserWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            database.collection("Users")
                                    .document().set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                }
                            });
                            Toast.makeText(SignupActivity.this, "Account is created.", Toast.LENGTH_SHORT).show();
                            email.getText().clear();
                            password.getText().clear();
                            name.getText().clear();
//                            startActivity(new Intent( SignupActivity.this, LoginActivity.class));

                        } else {
                            Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
    }
}