package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnit, toUnit;
    private TextView resultText;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        Button convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        // Populate spinners with unit options
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        // Handle Convert Button Click
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String from = fromUnit.getSelectedItem().toString();
        String to = toUnit.getSelectedItem().toString();
        double input = Double.parseDouble(inputValue.getText().toString());

        double result = convert(from, to, input);
        resultText.setText("Result: " + result + " " + to);
    }

    private double convert(String from, String to, double value) {
        double meters = switch (from) {
            case "Feet" -> value * 0.3048;
            case "Inches" -> value * 0.0254;
            case "Centimeters" -> value * 0.01;
            case "Meters" -> value;
            case "Yards" -> value * 0.9144;
            default -> 0;
        };

        return switch (to) {
            case "Feet" -> meters / 0.3048;
            case "Inches" -> meters / 0.0254;
            case "Centimeters" -> meters / 0.01;
            case "Meters" -> meters;
            case "Yards" -> meters / 0.9144;
            default -> 0;
        };
    }
}
