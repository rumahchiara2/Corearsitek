package com.nunucore.corearsitek;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class LanguageSelectActivity extends AppCompatActivity {

    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        baseUrl = getIntent().getStringExtra("baseUrl");
        if (baseUrl == null) baseUrl = "https://corearsitek.id";

        // Setup blur
        setupBlur(findViewById(R.id.blurEnglish));
        setupBlur(findViewById(R.id.blurIndonesia));

        CardView cardEnglish = findViewById(R.id.cardEnglish);
        CardView cardIndonesia = findViewById(R.id.cardIndonesia);
        ImageView flagEnglish = findViewById(R.id.flagEnglish);
        ImageView flagIndonesia = findViewById(R.id.flagIndonesia);

        cardEnglish.setOnClickListener(v -> {
            flipFlag(flagEnglish);
            openMainActivity(baseUrl + "/");
        });

        cardIndonesia.setOnClickListener(v -> {
            flipFlag(flagIndonesia);
            openMainActivity(baseUrl + "/id/");
        });
    }

    private void setupBlur(BlurView blurView) {
        float radius = 18f;
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        Drawable windowBackground = getWindow().getDecorView().getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);
    }

    private void flipFlag(ImageView flagView) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(flagView, "rotationY", 0f, 180f);
        flip.setDuration(600);
        flip.start();
    }

    private void openMainActivity(String url) {
        Intent intent = new Intent(LanguageSelectActivity.this, MainActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
