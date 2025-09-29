package com.nunucore.corearsitek;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Setup Blur untuk Core Arsitek
        BlurView blurArsitek = findViewById(R.id.blurCardArsitek);
        setupBlur(blurArsitek);

        // Setup Blur untuk Core Studio
        BlurView blurStudio = findViewById(R.id.blurCardStudio);
        setupBlur(blurStudio);

        // Shine untuk Core Arsitek
        ImageView shineArsitek = findViewById(R.id.shineArsitek);
        Animation animArsitek = AnimationUtils.loadAnimation(this, R.anim.shine_animation);
        shineArsitek.startAnimation(animArsitek);

        // Shine untuk Core Studio
        ImageView shineStudio = findViewById(R.id.shineStudio);
        Animation animStudio = AnimationUtils.loadAnimation(this, R.anim.shine_animation);
        shineStudio.startAnimation(animStudio);
    }

    private void setupBlur(BlurView blurView) {
        float radius = 20f;

        View decorView = getWindow().getDecorView();
        View rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith((View) rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);
    }
}
