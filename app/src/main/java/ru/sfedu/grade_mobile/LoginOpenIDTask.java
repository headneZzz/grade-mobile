package ru.sfedu.grade_mobile;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class LoginOpenIDTask extends AsyncTask<String, Void, String> {
    AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... strings) {
        String s = "";
        try {
            s = Jsoup.connect("http://grade.sfedu.ru/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get().title();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
