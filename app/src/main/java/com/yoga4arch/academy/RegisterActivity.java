package com.yoga4arch.academy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends Activity {

    private EditText edtUsername, edtEmail, edtPassword, edtFullname;
    private Button btnRegister, btnGoLogin;
    private ProgressBar progress;
    private TextView tvMessage;

    // Endpoint plugin custom
    private static final String REGISTER_URL_PLUGIN = "https://yoga4archacademy.cloud/wp-json/yoga4arch/v1/register";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtFullname = findViewById(R.id.edtFullname);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoLogin = findViewById(R.id.btnGoLogin);
        progress = findViewById(R.id.progress);
        tvMessage = findViewById(R.id.tvMessage);

        btnRegister.setOnClickListener(v -> attemptRegister());
        btnGoLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void attemptRegister() {
        tvMessage.setText("");
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();
        String fullname = edtFullname.getText().toString().trim();

        if (!validate(username, email, password)) return;

        progress.setVisibility(View.VISIBLE);
        btnRegister.setEnabled(false);

        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
            json.put("email", email);
            json.put("password", password);
            if (!TextUtils.isEmpty(fullname)) json.put("name", fullname);
        } catch (JSONException e) {
            showError("JSON error: " + e.getMessage());
            return;
        }

        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(REGISTER_URL_PLUGIN)
                .post(body)
                .header("Accept", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> showError("Network error: " + e.getMessage()));
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                final String respBody = response.body() != null ? response.body().string() : "";
                runOnUiThread(() -> {
                    progress.setVisibility(View.GONE);
                    btnRegister.setEnabled(true);
                    if (response.isSuccessful()) {
                        tvMessage.setText("Registrasi berhasil. Silakan login.");
                        tvMessage.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

                        // ⬇️ Redirect otomatis ke LoginActivity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        String msg = parseMessage(respBody);
                        tvMessage.setText("Gagal: " + msg + " (HTTP " + response.code() + ")");
                        tvMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    }
                });
            }
        });
    }

    private boolean validate(String username, String email, String password) {
        if (TextUtils.isEmpty(username) || username.length() < 3) {
            edtUsername.setError("Username minimal 3 karakter");
            return false;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Email tidak valid");
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            edtPassword.setError("Password minimal 8 karakter");
            return false;
        }
        return true;
    }

    private void showError(String msg) {
        runOnUiThread(() -> {
            progress.setVisibility(View.GONE);
            btnRegister.setEnabled(true);
            tvMessage.setText(msg);
            tvMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        });
    }

    private String parseMessage(String body) {
        if (body == null) return "No response body";
        try {
            JSONObject j = new JSONObject(body);
            if (j.has("message")) return j.optString("message");
            if (j.has("data")) return j.optString("data");
            if (j.has("code")) return j.optString("code");
            return body;
        } catch (JSONException e) {
            return body.length() > 200 ? body.substring(0, 200) : body;
        }
    }
}
