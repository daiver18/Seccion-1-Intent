package com.example.daiverandresdoria.app_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private Button btnAct2;
    private Button btnAct3;
    private final String SENDTEXT = "hello from the MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obligar icono en la barra superior
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        txt=findViewById(R.id.name);
        btnAct2=findViewById(R.id.btnActivity2);
        btnAct3=findViewById(R.id.btnActivity3);

        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Hello Word");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("sendtext", SENDTEXT);
                startActivity(intent);

            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Hello Word");
                Intent intent = new Intent(MainActivity.this, thirdActivity.class);
                intent.putExtra("sendtext", SENDTEXT);
                startActivity(intent);

            }
        });
    }

}
