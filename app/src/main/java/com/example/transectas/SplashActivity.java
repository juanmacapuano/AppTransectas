package com.example.transectas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       Animation animationUp = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_up);
       Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_down);

        TextView tv_developer_splash = findViewById(R.id.tv_developer_splash);
        TextView tv_title_app = findViewById(R.id.tv_title_app);
        TextView tv_version_splash = findViewById(R.id.tv_version_splash);
        ImageView iv_logo_splash = findViewById(R.id.iv_logo_splash);

        tv_developer_splash.setAnimation(animationDown);
        tv_title_app.setAnimation(animationDown);
        tv_version_splash.setAnimation(animationDown);
        iv_logo_splash.setAnimation(animationUp);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, ProjectActivity.class);
            startActivity(intent);
            finish();
        },4000);
    }
}