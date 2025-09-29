package com.nunucore.corearsitek;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CardView cardCoreArsitek = findViewById(R.id.cardCoreArsitek);
        CardView cardCoreStudio = findViewById(R.id.cardCoreStudio);

        cardCoreArsitek.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("url", "https://corearsitek.id");
            startActivity(intent);
        });

        cardCoreStudio.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("url", "https://corestudio.id");
            startActivity(intent);
        });
    }
}
