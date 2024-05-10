package com.example.a10thguidedexcercise;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    WebView browser;
    AutoCompleteTextView suggestedURL;
    ArrayAdapter adapter;
    Button submit;
    String [] urls = {"google.com","yahoo.com","facebook.com","youtube.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browser = findViewById(R.id.webView);
        suggestedURL = findViewById(R.id.actvURLGE10);
        submit = findViewById(R.id.btnOpenURLGE10);
        textView = findViewById(R.id.textView9);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,urls);
        suggestedURL.setThreshold(2);
        suggestedURL.setAdapter(adapter);

        initializeWebView();
        loadWebURL();

    }

    public void initializeWebView(){
        browser.getSettings().setLoadsImagesAutomatically(true);
        // enabled java script
        browser.getSettings().setJavaScriptEnabled(true);
        // Android webview launches browser when calling loadurl
        browser.setWebViewClient(new WebViewClient());
        // enabled Scrollbar
        browser.setScrollBarStyle(browser.SCROLLBARS_INSIDE_OVERLAY);
    }

    public void loadWebURL(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = suggestedURL.getText().toString();
                String[] urlExtensions = {".com", ".org", ".ph", ".edu", ".io"};

                if(url.startsWith("www.")) {
                    url = "https://" + url;
                }
                for (String value : urlExtensions) {
                    if (url.contains(value)) {
                        url = "https://www." + url;
                        browser.loadUrl(url);
                        return;
                    }
                }
                for (String value : urlExtensions) {
                    if (!url.contains(value)) {
                        url = googleSearch(url);
                        break;
                    }
                }
//                submit.setVisibility(View.GONE);
//                suggestedURL.setVisibility(View.GONE);
//                textView.setVisibility(View.GONE);
                browser.loadUrl(url);
            }
        });
    }

    public String googleSearch(String search) {
        return "https://www.google.com/search?q=" + search +"&sca_esv=c4520c9ea19d39e2&ei=iQU-Zur6Kf6ZseMPhPmo0A8&ved=0ahUKEwiq44CA_oKGAxX-TGwGHYQ8CvoQ4dUDCBA&uact=5&oq=sdwa&gs_lp=Egxnd3Mtd2l6LXNlcnAiBHNkd2EyBRAAGIAEMgsQABiABBixAxiDATIFEAAYgAQyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAEMgUQABiABDIFEAAYgAQyBRAAGIAESPkIUABYgQNwAHgBkAEAmAGnAqABiwaqAQUwLjMuMbgBA8gBAPgBAZgCBKACtgbCAgoQABiABBhDGIoFwgINEC4YgAQYQxjUAhiKBcICCxAAGIAEGJECGIoFwgIIEAAYgAQYsQPCAhEQLhiABBixAxiDARjHARivAZgDAJIHBzAuMy4wLjGgB7MX&sclient=gws-wiz-serp";
    }
}
