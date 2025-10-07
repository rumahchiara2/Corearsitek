package com.nunucore.corearsitek;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LanguageSelectActivity extends AppCompatActivity {

    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);

        // Ambil baseUrl dari Intent, dengan default jika null
        baseUrl = getIntent().getStringExtra("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "https://corearsitek.id";
        }

        // Inisialisasi view
        CardView cardEnglish = findViewById(R.id.cardEnglish);
        CardView cardIndonesia = findViewById(R.id.cardIndonesia);
        ImageView flagEnglish = findViewById(R.id.flagEnglish);
        ImageView flagIndonesia = findViewById(R.id.flagIndonesia);
        TextView textEnglish = findViewById(R.id.textEnglish);
        TextView textIndonesia = findViewById(R.id.textIndonesia);

        // Pastikan semua view ada
        if (cardEnglish == null || cardIndonesia == null) {
            finish(); // jika layout tidak ditemukan, langsung keluar dengan aman
            return;
        }

        // Klik English
        cardEnglish.setOnClickListener(v -> {
            startFlagSpin(flagEnglish);
            openMainActivity(baseUrl + "/");
        });

        // Klik Indonesia
        cardIndonesia.setOnClickListener(v -> {
            startFlagSpin(flagIndonesia);
            openMainActivity(baseUrl + "/id/");
        });
    }

    private void startFlagSpin(ImageView flagView) {
        if (flagView == null) return;

        RotateAnimation rotate = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(600);
        flagView.startAnimation(rotate);
    }

    private void openMainActivity(String url) {
        Intent intent = new Intent(LanguageSelectActivity.this, MainActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
