package ru.team_grade.grade_mobile;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

//FIXME Main activity должен открываться только 1 раз, вне зависимости, сколько раз нажата кнопка
//FIXME Если нажать "Назад", то Login activity открываться не должен
//TODO Сохранять данные пользователя, чтобы не вводить все время логин и пароль
//TODO Реализовать парсер и красиво все оформить
//TODO Сохранять данные в бд, чтобы просматривать информацию оффлайн
//TODO Экран логина сделать покрасивее
//TODO Добавить расписание

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
