package ru.team_grade.grade_mobile;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie("openid.sfedu.ru", "openid_server=" + User.token);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://grade.sfedu.ru/handler/sign/openidlogin?loginopenid=" + User.login);
    }
}
