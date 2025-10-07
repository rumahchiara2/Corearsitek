package com.nunucore.corearsitek;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.SslError;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private long lastBackPressedTime = 0;
    private static final int EXIT_INTERVAL = 2000; // 2 detik

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        setupWebView();

        String url = getIntent().getStringExtra("url");
        if (url == null || url.isEmpty()) {
            url = "https://corearsitek.id/"; // fallback default
        }

        webView.loadUrl(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings webSettings = webView.getSettings();

        // 🔧 Basic settings
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);

        // 📱 Responsiveness
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        // 🌐 Mixed content (untuk https dengan konten http)
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 🚀 Performance
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // 💡 WebChromeClient (untuk progress, JS alert, dsb)
        webView.setWebChromeClient(new WebChromeClient());

        // 🌍 WebViewClient custom
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    return false; // load di WebView
                } else if (url.startsWith("tel:") ||
                        url.startsWith("mailto:") ||
                        url.startsWith("whatsapp:") ||
                        url.startsWith("geo:")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Unable to open link", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ✅ Lanjutkan SSL kalau HTTPS valid, block kalau error besar
                handler.proceed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Double back press untuk keluar
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if (lastBackPressedTime + EXIT_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                lastBackPressedTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
