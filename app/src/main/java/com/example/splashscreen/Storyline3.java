package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Storyline3 extends AppCompatActivity {
    private Button st3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyline3);

        st3 = findViewById(R.id.st3_btn);
        st3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Storyline3.this, SaveTheTurtle.class);
                startActivity(intent);
            }
        });
    }
}