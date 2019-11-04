package ru.sfedu.grade_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import static ru.sfedu.grade_mobile.User.*;

public class MainActivity extends AppCompatActivity implements AsyncResponse {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User("USER_HERE","PASS_HERE",this);
    }

    @Override
    public void processFinish(String output) {
        if (token != null) {
            WebView webView = findViewById(R.id.webView);
            if (response.contains("Вы вошли как")) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setCookie("openid.sfedu.ru", "openid_server=" + token);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://grade.sfedu.ru/handler/sign/openidlogin?loginopenid=" + login);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Неверный логин или пароль!", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Проверьте подлючение к интернету!", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}
