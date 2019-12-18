package ru.team_grade.grade_mobile;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class LoginTask extends AsyncTask<String, Void, String> {
    AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... strings) {
        Connection.Response res = null;
        try {
            res = Jsoup.connect("https://openid.sfedu.ru/server.php/login")
                    .data("openid_url", User.login)
                    .data("password", User.pass)
                    .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res == null) return null;
        User.token = res.cookie("openid_server");

        try {
            User.response = res.parse().text();
            Element element = Jsoup.connect("http://grade.sfedu.ru/handler/sign/openidlogin?loginopenid=" + User.login)
                    .cookie("openid_server", User.token)
                    .get()
                    .body()
                    .getElementsByClass("profile_wrapper")
                    .get(0);
            User.degree = element
                    .getElementsByClass("profile_delimeter")
                    .text();
            User.name = element.getElementsByClass("username").text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return User.token;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
