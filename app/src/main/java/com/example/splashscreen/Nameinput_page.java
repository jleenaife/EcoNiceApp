package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Nameinput_page extends AppCompatActivity {
    private EditText nameText;
    private Button button;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nameinput_page);

        nameText = findViewById(R.id.name_Txtinput);
        button = findViewById(R.id.Homepage_Btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    public void sendData() {
        name = nameText.getText().toString().trim();

        Intent i = new Intent(this, Homepage.class);
        i.putExtra(Homepage.NAME,name);
        startActivity(i);
    }
}