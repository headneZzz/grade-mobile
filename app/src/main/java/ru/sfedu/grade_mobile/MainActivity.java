package ru.sfedu.grade_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static ru.sfedu.grade_mobile.LoginOpenIDTask.login;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    public void startLoginTask(String login, String pass) {
        LoginOpenIDTask task = new LoginOpenIDTask(login, pass);
        task.delegate = this;
        task.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLoginTask("user", "pass");
    }

    @Override
    public void processFinish(String output) {
        WebView webView = findViewById(R.id.webView);
        if (LoginOpenIDTask.response.contains("Вы вошли как")) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie("openid.sfedu.ru", "openid_server=" + LoginOpenIDTask.token);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://grade.sfedu.ru/handler/sign/openidlogin?loginopenid=" + login);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Обновите пароль!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
