package com.example.daiverandresdoria.app_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private TextView txt;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt=findViewById(R.id.textView);
        btn=findViewById(R.id.button);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String sendtext = bundle.getString("sendtext");
            Toast.makeText(this, sendtext, Toast.LENGTH_SHORT).show();
            txt.setText("hello!!");
        }else{
            Toast.makeText(this, "it is empty", Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, thirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
