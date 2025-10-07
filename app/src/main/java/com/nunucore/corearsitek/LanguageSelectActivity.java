package com.nunucore.corearsitek;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.CardView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LanguageSelectActivity extends AppCompatActivity {

    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        baseUrl = getIntent().getStringExtra("baseUrl");
        if (baseUrl == null) baseUrl = "https://corearsitek.id";

        CardView cardEnglish = findViewById(R.id.cardEnglish);
        CardView cardIndonesia = findViewById(R.id.cardIndonesia);
        ImageView flagEnglish = findViewById(R.id.flagEnglish);
        ImageView flagIndonesia = findViewById(R.id.flagIndonesia);

        // Animasi spin ketika diklik
        cardEnglish.setOnClickListener(v -> {
            animateSpin(flagEnglish);
            openWeb(baseUrl);
        });

        cardIndonesia.setOnClickListener(v -> {
            animateSpin(flagIndonesia);
            openWeb(baseUrl + "/id/");
        });
    }

    private void animateSpin(ImageView imageView) {
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(500);
        imageView.startAnimation(rotate);
    }

    private void openWeb(String url) {
        Intent intent = new Intent(LanguageSelectActivity.this, MainActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        finish();
    }
}
