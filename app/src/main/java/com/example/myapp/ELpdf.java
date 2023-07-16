package com.example.myapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ELpdf extends AppCompatActivity {

    WebView webView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_lpdf);

        webView=findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);

        String pdfname=getIntent().getStringExtra("pdfname");
        String pdfurl=getIntent().getStringExtra("pdfurl");

        /* Uri uri= Uri.parse(pdfurl);
        pdfView.fromUri(uri);*/

        getSupportActionBar().setTitle(pdfname);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
            }
        });


        String urlpath=pdfurl;
        try {
            urlpath= URLEncoder.encode(pdfurl,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

       // webView.loadUrl("http://docs.google.com/gview?url="+urlpath);
        webView.loadUrl("http://docs.google.com/viewerng/viewer?embedded=true&url="+urlpath);
    }
}