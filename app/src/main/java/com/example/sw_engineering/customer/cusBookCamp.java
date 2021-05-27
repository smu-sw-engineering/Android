package com.example.sw_engineering.customer;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;

public class cusBookCamp extends AppCompatActivity {
    private Button book_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_book_camp);

        book_button = (Button)findViewById((R.id.book));
    }

}
