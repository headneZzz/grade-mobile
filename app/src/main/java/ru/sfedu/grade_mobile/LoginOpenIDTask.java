package ru.sfedu.grade_mobile;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;


public class LoginOpenIDTask extends AsyncTask<String, Void, String> {
    AsyncResponse delegate = null;
    static String login;
    private String pass;
    static String token;
    static String response;

    LoginOpenIDTask(String login, String pass) {
        this.login = login;
        this.pass = pass;
        token = null;
    }

    @Override
    protected String doInBackground(String... strings) {
        Connection.Response res = null;
        try {
            res = Jsoup.connect("https://openid.sfedu.ru/server.php/login")
                    .data("openid_url", login)
                    .data("password", pass)
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        token = res.cookie("openid_server");
        try {
            response = res.parse().text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
