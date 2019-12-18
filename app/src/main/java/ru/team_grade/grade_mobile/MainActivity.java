package ru.team_grade.grade_mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static ru.team_grade.grade_mobile.LoginActivity.sPref;

//TODO Не показывать экран логина при сохраненных данных
//TODO Реализовать парсер и красиво все оформить
//TODO Сохранять данные в бд, чтобы просматривать информацию оффлайн
//TODO Экран логина сделать покрасивее
//TODO Добавить расписание
//FIXME Не работает кнопка "Выход"

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    @SuppressLint("StaticFieldLeak")
    static WebView webView;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //FIXME
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView nav_name = headerView.findViewById(R.id.nav_name);
        nav_name.setText(User.name);
        TextView nav_degree = headerView.findViewById(R.id.nav_degree);
        nav_degree.setText(User.degree);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_main).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //FIXME
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        System.out.println(menuItem.getItemId());
        if (menuItem.getItemId() == R.id.log_out) {
            System.out.println("fuck");
            sPref.edit().remove("login").remove("pass").apply();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        return false;
    }

}
