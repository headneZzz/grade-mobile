package ru.team_grade.grade_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements AsyncResponse {
    static SharedPreferences sPref;
    ProgressDialog dialog;
    private EditText loginEditText;
    private EditText passwordEditText;
    public static final String loginPref = "pref";
    final String SAVED_LOGIN = "login";
    final String SAVED_PASS = "pass";
    private boolean isLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLog = false;
        sPref = getSharedPreferences(loginPref, Context.MODE_PRIVATE);
        if (sPref.contains(SAVED_LOGIN) && sPref.contains(SAVED_PASS)) {
            isLog = true;
            createUser(sPref.getString(SAVED_LOGIN, ""), sPref.getString(SAVED_PASS, ""));
        }
        setContentView(R.layout.activity_login);
        Toolbar mActionBarToolbar = findViewById(R.id.loginToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Авторизация");
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(loginEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

    }

    protected void createUser(String login, String password) {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Загрузка");
        dialog.setCancelable(true);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        new User(login, password, this);
    }

    @Override
    public void processFinish(String output) {
        if (User.token != null) {
            sPref = getSharedPreferences(loginPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            if (User.response.contains("Вы вошли как")) {
                if (!isLog) {
                    editor.putString(SAVED_LOGIN, loginEditText.getText().toString());
                    editor.putString(SAVED_PASS, passwordEditText.getText().toString());
                    editor.apply();
                }
                dialog.hide();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                editor.clear().apply();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Неверный логин или пароль!", Toast.LENGTH_SHORT);
                dialog.hide();
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Проверьте подлючение к интернету!", Toast.LENGTH_SHORT);
            dialog.hide();
            toast.show();
        }
    }
}
