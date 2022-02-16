package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.youtube.com/watch?v=At1J3_WLK_A");
        webView.setWebViewClient(new MyWebViewClient());
    }

    public void onClick(View view) {
        Intent intent = new
                Intent("ru.alexanderklimov.Browser");
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=At1J3_WLK_A"));
        startActivity(intent);
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean ShouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().endsWith("https://logiclike.com/cabinet#/messages/events")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
    public class MyAppWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getHost().length() == 0) {
                return false;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        }
    }


}

