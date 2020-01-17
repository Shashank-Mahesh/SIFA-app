package org.sifa.application.sifa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

public class PurchaseTickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_tickets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String link;
        if (getIntent().getExtras().isEmpty()) {
            link = "https://mycity.sulekha.com/sifa-2016-sponsor-membership_buy_2091234";
        } else {
            link = (String) getIntent().getExtras().get("link");
        }
        WebView webView = (WebView) findViewById(R.id.ticket_webview);

        webView.loadUrl(link);
    }

}
