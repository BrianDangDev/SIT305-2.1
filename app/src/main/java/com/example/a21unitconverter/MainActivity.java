package com.example.a21unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceSpinner, destSpinner, weightSourceSpinner, weightDestSpinner, temSourceSpinner, temDesSpinner;
    private EditText inputEditText, weightInputEditText, temInputEditText;
    private TextView resultTextView, weightResultTextView, temResultTextView;
    private Button convertButton, weightConvertButton, temConvertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////// Length Conversion////////////////////
        sourceSpinner = findViewById(R.id.length_source_unit_spinner);
        destSpinner = findViewById(R.id.length_dest_unit_spinner);
        inputEditText = findViewById(R.id.length_input_value_edittext);
        resultTextView = findViewById(R.id.length_result_textview);
        convertButton = findViewById(R.id.length_convert_button);
        // Create an adapter with the desired source and destination length units
        ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource(this, R.array.source_length_units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(adapter);

        ArrayAdapter < CharSequence > destAdapter = ArrayAdapter.createFromResource(this, R.array.dest_length_units_array, android.R.layout.simple_spinner_item);
        destAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destSpinner.setAdapter(destAdapter);

        //////////////////// Weight Conversion////////////////////
        weightSourceSpinner = findViewById(R.id.weight_source_unit_spinner);
        weightDestSpinner = findViewById(R.id.weight_dest_unit_spinner);
        weightInputEditText = findViewById(R.id.weight_input_value_edittext);
        weightResultTextView = findViewById(R.id.weight_result_textview);
        weightConvertButton = findViewById(R.id.weight_convert_button);

        // Create an adapter with the desired source and destination weight units
        ArrayAdapter < CharSequence > weightSourceAdapter = ArrayAdapter.createFromResource(this, R.array.source_weight_units_array, android.R.layout.simple_spinner_item);
        weightSourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightSourceSpinner.setAdapter(weightSourceAdapter);

        ArrayAdapter < CharSequence > weightDestAdapter = ArrayAdapter.createFromResource(this, R.array.dest_weight_units_array, android.R.layout.simple_spinner_item);
        weightDestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightDestSpinner.setAdapter(weightDestAdapter);

        //////////////////// Temperature Conversion////////////////////
        temSourceSpinner = findViewById(R.id.temperature_source_unit_spinner);
        temDesSpinner = findViewById(R.id.temperature_dest_unit_spinner);
        temInputEditText = findViewById(R.id.temperature_input_value_edittext);
        temResultTextView = findViewById(R.id.temperature_result_textview);
        temConvertButton = findViewById(R.id.temperature_convert_button);

        // Create an adapter with the desired source and destination temperature units
        ArrayAdapter < CharSequence > tempSourceAdapter = ArrayAdapter.createFromResource(this, R.array.source_temp_units_array, android.R.layout.simple_spinner_item);
        tempSourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temSourceSpinner.setAdapter(tempSourceAdapter);

        ArrayAdapter < CharSequence > tempDestAdapter = ArrayAdapter.createFromResource(this, R.array.dest_temp_units_array, android.R.layout.simple_spinner_item);
        tempDestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temDesSpinner.setAdapter(tempDestAdapter);

        temConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temConvert();
            }
        });
        weightConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightConvert();
            }
        });
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lengthConvert();
            }
        });
    }

    private void temConvert() {
        // Get the input value and selected units
        String input = temInputEditText.getText().toString();
        String sourceUnit = temSourceSpinner.getSelectedItem().toString();
        String destUnit = temDesSpinner.getSelectedItem().toString();

        // Check if input value is not empty
        if (input.isEmpty()) {
            temResultTextView.setText("");
            return;
        }
        // Convert to Celsius
        double celsius = Double.parseDouble(input);

        if (sourceUnit.equals("Fahrenheit")) {
            celsius = (celsius - 32) / 1.8;
        } else if (sourceUnit.equals("Kelvin")) {
            celsius = celsius - 273.15;
        }
        double result = celsius;

        if (destUnit.equals("Fahrenheit")) {
            result = (celsius * 1.8) + 32;
        } else if (destUnit.equals("Kelvin")) {
            result = celsius + 273.15;
        }
        temResultTextView.setText(String.format("%.2f", result));
    }

    private void weightConvert() {
        // Get the source and desitnation unit from spinners
        String sourceUnit = weightSourceSpinner.getSelectedItem().toString();
        String destUnit = weightDestSpinner.getSelectedItem().toString();

        // Get the input and convert input to a double
        String inputValueString = weightInputEditText.getText().toString();
        double inputValue = Double.parseDouble(inputValueString);
        double convertedValue = 0.0;
        double conversionFactor = 0.0;

        // Define the conversion value
        if (sourceUnit.equals("Kilo") && destUnit.equals("Pound")) {
            conversionFactor = 2.20462;
        } else if (sourceUnit.equals("Kilo") && destUnit.equals("Ounce")) {
            conversionFactor = 35.274;
        } else if (sourceUnit.equals("Kilo") && destUnit.equals("Ton")) {
            conversionFactor = 0.00110231;
        }

        convertedValue = inputValue * conversionFactor;

        // Display the converted value
        weightResultTextView.setText(String.format("%.2f", convertedValue));
    }

    private void lengthConvert() {
        String sourceUnit = sourceSpinner.getSelectedItem().toString();
        String destUnit = destSpinner.getSelectedItem().toString();
        String inputValueStr = inputEditText.getText().toString();
        double result = 0.0;
        if (inputValueStr.isEmpty()) {
            resultTextView.setText("Please enter a value");
            return;
        }
        double inputValue;
        try {
            inputValue = Double.parseDouble(inputValueStr);
        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input value");
            return;
        }
        if (sourceUnit.equals("Centimeter") && destUnit.equals("Inch")) {
            result = inputValue / 2.54;
        } else if (sourceUnit.equals("Centimeter") && destUnit.equals("Foot")) {
            result = inputValue / 30.48;
        } else if (sourceUnit.equals("Centimeter") && destUnit.equals("Yard")) {
            result = inputValue / 91.44;
        } else if (sourceUnit.equals("Centimeter") && destUnit.equals("Mile")) {
            result = inputValue / 160934.4;
        } else if (sourceUnit.equals("Kilometer") && destUnit.equals("Inch")) {
            result = inputValue * 39370.079;
        } else if (sourceUnit.equals("Kilometer") && destUnit.equals("Foot")) {
            result = inputValue * 3280.84;
        } else if (sourceUnit.equals("Kilometer") && destUnit.equals("Yard")) {
            result = inputValue * 1093.613;
        } else if (sourceUnit.equals("Kilometer") && destUnit.equals("Mile")) {
            result = inputValue / 1.609344;
        } else {

            result = inputValue;
        }

        resultTextView.setText(String.format("%.2f %s = %.2f %s", inputValue, sourceUnit, result, destUnit));

    }

}