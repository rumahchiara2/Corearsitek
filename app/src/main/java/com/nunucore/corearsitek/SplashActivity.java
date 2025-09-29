package com.nunucore.corearsitek;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView shineArsitek;
    private ImageView shineStudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Shine untuk Core Arsitek
        shineArsitek = findViewById(R.id.shineArsitek);
        Animation animArsitek = AnimationUtils.loadAnimation(this, R.anim.shine_animation);
        shineArsitek.startAnimation(animArsitek);

        // Shine untuk Core Studio
        shineStudio = findViewById(R.id.shineStudio);
        Animation animStudio = AnimationUtils.loadAnimation(this, R.anim.shine_animation);
        shineStudio.startAnimation(animStudio);
    }
}
