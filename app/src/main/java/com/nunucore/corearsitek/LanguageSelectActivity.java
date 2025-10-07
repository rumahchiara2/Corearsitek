package com.nunucore.corearsitek;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LanguageSelectActivity extends AppCompatActivity {

    private CardView cardEnglish, cardIndonesia;
    private ImageView flagEnglish, flagIndonesia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        cardEnglish = findViewById(R.id.cardEnglish);
        cardIndonesia = findViewById(R.id.cardIndonesia);
        flagEnglish = findViewById(R.id.flagEnglish);
        flagIndonesia = findViewById(R.id.flagIndonesia);

        // === CARD ENGLISH ===
        cardEnglish.setOnClickListener(v -> {
            spinFlag(flagEnglish);
            openWeb("https://corearsitek.id/");
        });

        // === CARD INDONESIA ===
        cardIndonesia.setOnClickListener(v -> {
            spinFlag(flagIndonesia);
            openWeb("https://corearsitek.id/id/");
        });
    }

    private void spinFlag(ImageView flag) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(flag, "rotationY", 0f, 360f);
        rotation.setDuration(400); // durasi 400ms
        rotation.start();
    }

    private void openWeb(String url) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
