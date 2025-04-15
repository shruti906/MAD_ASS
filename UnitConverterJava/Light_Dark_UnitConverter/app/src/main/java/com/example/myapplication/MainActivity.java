package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnit, toUnit;
    private TextView resultText;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("settings_pref", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);

        AppCompatDelegate.setDefaultNightMode(
                isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        Button convertButton = findViewById(R.id.convertButton);
        Button settingsButton = findViewById(R.id.settingsButton);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertButton.setOnClickListener(v -> convertUnits());

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    private void convertUnits() {
        String from = fromUnit.getSelectedItem().toString();
        String to = toUnit.getSelectedItem().toString();
        String inputStr = inputValue.getText().toString();

        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        double input = Double.parseDouble(inputStr);
        double result = convert(from, to, input);

        resultText.setText("Result: " + result + " " + to);
    }

    private double convert(String from, String to, double value) {
        double meters = 0;

        switch (from) {
            case "Feet": meters = value * 0.3048; break;
            case "Inches": meters = value * 0.0254; break;
            case "Centimeters": meters = value * 0.01; break;
            case "Meters": meters = value; break;
            case "Yards": meters = value * 0.9144; break;
        }

        switch (to) {
            case "Feet": return meters / 0.3048;
            case "Inches": return meters / 0.0254;
            case "Centimeters": return meters / 0.01;
            case "Meters": return meters;
            case "Yards": return meters / 0.9144;
        }
        return 0;
    }
}
