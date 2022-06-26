package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    public static final String NAME = "NAME";
    private TextView nameText;
    private String name;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        nameText = findViewById(R.id.nameText);

        Intent i = getIntent();
        name = i.getStringExtra(NAME);

        nameText.setText(name+"!");

        button = (Button) findViewById(R.id.startBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStart();
            }
        });

        button = (Button) findViewById(R.id.htpBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHTP();
            }
        });

        button = (Button) findViewById(R.id.abtBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout();
            }
        });

        button = (Button) findViewById(R.id.quitBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitGame();
            }
        });
    }

    public void openStart() {
        Intent intent = new Intent(this, Startpage.class);
        startActivity(intent);
    }

    public void openHTP() {
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }

    public void openAbout() {
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
    public void ExitGame() {
        Intent intent = new Intent(this, Quit.class);
        startActivity(intent);
    }
}