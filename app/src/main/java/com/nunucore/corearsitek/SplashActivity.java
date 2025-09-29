package com.nunucore.corearsitek;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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
        startShineAnimation(shineArsitek);

        // Shine untuk Core Studio
        ImageView shineStudio = findViewById(R.id.shineStudio);
        startShineAnimation(shineStudio);

        // Klik card CoreArsitek
        View cardArsitek = findViewById(R.id.cardCoreArsitek);
        cardArsitek.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("url", "https://corearsitek.id");
            startActivity(intent);
        });

        // Klik card CoreStudio
        View cardStudio = findViewById(R.id.cardCoreStudio);
        cardStudio.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("url", "https://corestudio.id");
            startActivity(intent);
        });
    }

    private void setupBlur(BlurView blurView) {
        float radius = 20f;
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        Drawable windowBackground = getWindow().getDecorView().getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);
    }

    private void startShineAnimation(ImageView shineView) {
        TranslateAnimation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1.5f,
                Animation.RELATIVE_TO_SELF, 1.5f,
                Animation.RELATIVE_TO_SELF, -1.5f,
                Animation.RELATIVE_TO_SELF, 1.5f
        );
        anim.setDuration(2500);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.RESTART);

        shineView.startAnimation(anim);
    }
}
