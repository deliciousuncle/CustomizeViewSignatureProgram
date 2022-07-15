package com.example.customize_view_signatureprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//參考資料 https://www.youtube.com/watch?v=8n6-FMC7I94&list=PLDwsR4fO8zMYegLEYctGSsmFTu0QkcU6I&index=50
    private Myview myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myview=findViewById(R.id.myview_);
        Button clear_btn=findViewById(R.id.clear_btn);
        Button ondo_btn=findViewById(R.id.ondo);

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.clear();
            }
        });

        ondo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             myview.undo();
            }
        });
    }
}