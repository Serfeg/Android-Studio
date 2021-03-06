package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonText = "{'name': 'Мурзик','color':-16777216,'age':9}";

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Cat murzik = gson.fromJson(jsonText, Cat.class);
        Log.i("GSON", "Имя: " + murzik.name + "\nВозраст: " + murzik.age);

        TextView cat_tv = findViewById(R.id.cat_tv);
        cat_tv.setText("Имя: " + murzik.name + " Возраст: " + murzik.age + " Цвет: ");
        View vColor = findViewById(R.id.vColor);
        vColor.setBackgroundColor(murzik.color);
    }
}