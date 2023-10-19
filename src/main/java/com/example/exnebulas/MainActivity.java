package com.example.exnebulas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView miVisorWeb;
    String url = "https://exnebulas.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miVisorWeb = findViewById(R.id.visorWeb); // Eliminado casting redundante
        final WebSettings ajustesVisorWeb = miVisorWeb.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        miVisorWeb.setWebViewClient(new Callback());
        Intent intent = getIntent();

        if (intent.getData() != null) {
            Uri uri = intent.getData();
            String urlclick = uri.toString();
            miVisorWeb.loadUrl(urlclick); // Eliminado 'toString()' redundante
        } else {
            miVisorWeb.loadUrl(url);
        }
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true; // Cambiado a 'true'
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignora los errores de SSL
        }
    }

    @Override
    public void onBackPressed() {
        if (miVisorWeb.canGoBack()) {
            miVisorWeb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
