package com.example.sw_engineering.owner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sw_engineering.R;

public class ownHome extends AppCompatActivity {
    private Button update_button, update_button1, schedule_button, schedule_button1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.own_home);
            update_button = findViewById(R.id.update_button);

            update_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(getApplicationContext(), .class);
                    //startActivity(intent);
                }
            });

    }

}
