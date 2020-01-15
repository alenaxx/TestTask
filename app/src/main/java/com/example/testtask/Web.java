package com.example.testtask;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Web  extends AppCompatActivity {
    private WebView webView;
    private Button jokeBtn;
    private TextView url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        this.setTitle("Api info");
        jokeBtn = (Button) findViewById(R.id.jokebtn);

        webView = (WebView)findViewById(R.id.webviw);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http:/www.icndb.com/api/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        jokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Web.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
    @Override
    public  void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();

        }
    }



}
