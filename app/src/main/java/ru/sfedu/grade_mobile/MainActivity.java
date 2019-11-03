package ru.sfedu.grade_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AsyncResponse{
    public void createNewText(){
        LoginOpenIDTask task = new LoginOpenIDTask();
        task.delegate=this;
        task.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewText();
    }

    @Override
    public void processFinish(String output) {
        TextView textView = findViewById(R.id.text);
        textView.setText(output);
    }
}
