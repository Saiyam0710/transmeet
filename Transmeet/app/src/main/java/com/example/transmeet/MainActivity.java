package com.example.transmeet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    ImageView logo;
//    TextView name;
//    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        logo = findViewById(R.id.imageView3);
//        name = findViewById(R.id.textView3);
//        lottieAnimationView = findViewById(R.id.lottie);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }, 1000 );
    }
}