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

        // Setup efek blur untuk kedua card
        setupBlur(findViewById(R.id.blurCardArsitek));
        setupBlur(findViewById(R.id.blurCardStudio));

        // Animasi shine untuk kedua card
        startShineAnimation(findViewById(R.id.shineArsitek));
        startShineAnimation(findViewById(R.id.shineStudio));

        // Klik card CoreArsitek → masuk ke LanguageSelectActivity
        findViewById(R.id.cardCoreArsitek).setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, LanguageSelectActivity.class);
            intent.putExtra("baseUrl", "https://corearsitek.id");
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // Klik card CoreStudio → langsung ke MainActivity (tanpa pilih bahasa)
        findViewById(R.id.cardCoreStudio).setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("url", "https://corestudio.id");
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void setupBlur(BlurView blurView) {
        if (blurView == null) return; // prevent crash jika ID tidak ditemukan

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
        if (shineView == null) return; // prevent crash jika ID tidak ditemukan

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
