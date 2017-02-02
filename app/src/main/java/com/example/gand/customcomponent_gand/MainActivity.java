package com.example.gand.customcomponent_gand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Porcentaje porcentaje = (Porcentaje) findViewById(R.id.porcentaje);
        porcentaje.setPorcentaje(5);
    }
}
