package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Startpage extends AppCompatActivity {
    public static final String NAME = "NAME";
    private TextView nameText;
    private Button button;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        nameText = findViewById(R.id.txtName);

        Intent i = getIntent();
        name = i.getStringExtra(NAME);

        nameText.setText("Before we start, "+name);

        button = (Button) findViewById(R.id.b12_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open12and();
            };
        });

        button = (Button) findViewById(R.id.a13_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open13();
            };
        });
    }

    public void open12and() {
        Intent intent = new Intent(this, below12home.class);
        startActivity(intent);
    }

    public void open13() {
        Intent intent = new Intent(this, above13.class);
        startActivity(intent);
    }
}