package ru.team_grade.grade_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static ru.team_grade.grade_mobile.MainActivity.webView;

public class HomeFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        webView = root.findViewById(R.id.webView);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie("openid.sfedu.ru", "openid_server=" + User.token);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://grade.sfedu.ru/handler/sign/openidlogin?loginopenid=" + User.login);
        return root;
    }
}