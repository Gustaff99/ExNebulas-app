package com.example.exnebulas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ClickActivity extends AppCompatActivity {
    WebView miVisorWeb;
    String url = "https://exnebulas.com/";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miVisorWeb = (WebView) findViewById(R.id.visorWeb);
        final WebSettings ajustesVisorWeb = miVisorWeb.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        miVisorWeb.setWebViewClient(new Callback());
        Intent intent = getIntent();

        if (intent.getData() != null) {
            Uri uri = intent.getData();
            String urlclick = uri.toString();
            miVisorWeb.loadUrl(urlclick.toString());
        }else {

            miVisorWeb.loadUrl(url);
        }

    }
    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return (false);
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
