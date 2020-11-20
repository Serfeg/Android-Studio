package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cat murzik = new Cat();
        murzik.name = "Мурзик";
        murzik.age = 9;
        murzik.color = Color.BLACK;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Log.i("GSON", gson.toJson(murzik));
    }
}