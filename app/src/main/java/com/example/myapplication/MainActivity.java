package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView resultViewText;
    Button calculateButton;
    private EditText lengthEditText;
    Button timeButton;
    Button tempButton;
    Button weightButton;
    RadioButton imperialToMetric;
    RadioButton metricToImperial;
    RadioButton inchesButton;
    RadioButton centimetersButton;
    RadioButton feetButton;
    RadioButton metersButton;
    RadioButton milesButton;
    RadioButton kilometersButton;


    //check what declaration to use/how to initialize
    /*double metersResult;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListeners();
    }

    private void findViews() {
        lengthEditText = findViewById(R.id.edit_text_input_length);
        calculateButton = findViewById(R.id.button_calculate_length);
        resultViewText = findViewById(R.id.text_view_result_length);
        timeButton = findViewById(R.id.button_convert_time);
        tempButton = findViewById(R.id.button_convert_temp);
        weightButton = findViewById(R.id.button_convert_weight);
        imperialToMetric = findViewById(R.id.radiobutton_from_imperial);
        metricToImperial = findViewById(R.id.radiobutton_from_metric);
        inchesButton = findViewById(R.id.radiobutton_inches);
        centimetersButton = findViewById(R.id.radiobutton_centimeters);
        milesButton = findViewById(R.id.radiobutton_miles);
        kilometersButton = findViewById(R.id.radiobutton_kilometers);
        metersButton = findViewById(R.id.radiobutton_meters);
        feetButton = findViewById(R.id.radiobutton_feet);
        //should be called 'viewText' or 'resultViewText' or resultText
    }

    private void setupButtonClickListeners() {

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inchesStrText = lengthEditText.getText().toString();

                if (inchesStrText.isEmpty()) {

                    String alertText = "Please enter a value";

                    Toast.makeText(MainActivity.this, alertText, Toast.LENGTH_LONG).show();
                }
                else {
                    //double result = convertToMeters(inchesStrText);
                    //displayResult(result);
                }

            }
        });
    }
    private boolean checkForMissingInputs(){
        if (imperialToMetric.isChecked()||metricToImperial.isChecked()){
            if (inchesButton.isChecked()||feetButton.isChecked()||milesButton.isChecked()){
                return centimetersButton.isChecked() || metersButton.isChecked() || kilometersButton.isChecked();
            }
        }
        return false;
    }
//    private boolean checkIfImperialToMetric(){
//        return imperialToMetric.isChecked();
//    }

    private void displayResult(double result) {
        DecimalFormat myFormatter = new DecimalFormat("0.00");
        String resultString = myFormatter.format(result);
        //resultViewText.setText(resultString + "meters");
    }

//    private double convertToMeters(String inchesStrText) {
//        int inches = Integer.parseInt(inchesStrText);
//        return inches / 39.37;
//    }
    //if inches
    //inches to Cm - Cm to inches
    //inches to meters - reverse
    //inches to km - reverse

    //if feet
    // feet to cm
    //feet to met
    //feet to km

    //if miles
    //miles to cm
    //miles to met
    //mi to km


}