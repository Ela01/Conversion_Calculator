package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

    }

    private void setupButtonClickListeners() {

        calculateButton.setOnClickListener(v -> {
            boolean allInputsChecked = checkForAllInputs();

            if (!allInputsChecked) {

                String alertText = "Please enter a value";

                Toast.makeText(MainActivity.this, alertText, Toast.LENGTH_LONG).show();
            } else {
                double result = calculateConversions();
                displayResult(result);
            }

        });
    }

    private boolean checkForAllInputs() {
        if ((imperialToMetric.isChecked() || metricToImperial.isChecked()) && !lengthEditText.getText().toString().isEmpty() &&
                (inchesButton.isChecked() || feetButton.isChecked() || milesButton.isChecked())) {
            return centimetersButton.isChecked() || metersButton.isChecked() || kilometersButton.isChecked();
        }
        return false;
    }




    //in to cm, in to m, in to km
    //feet to cm, ft to m, ft to km
    //mi to cm, mi to m, mi to km
    /* if in = true
     * */


    private double calculateConversions() {
        String inputString = lengthEditText.getText().toString();
        double inputDouble = Double.parseDouble(inputString);

        if (imperialToMetric.isChecked()) {
            if (inchesButton.isChecked()) {
                if (centimetersButton.isChecked()) { //in to cm
                    return inputDouble * 2.54;
                } else if (metersButton.isChecked()) { //in to m
                    return inputDouble * 0.0254;
                } else { //in to km
                    return inputDouble / 393.70;
                }
            } else if (feetButton.isChecked()) {
                if (centimetersButton.isChecked()) {
                    return inputDouble * 30.48;
                } else if (metersButton.isChecked()) {
                    return inputDouble * 0.3048;
                } else {
                    return inputDouble * 0.0003048;
                }
            } else {
                if (centimetersButton.isChecked()) {
                    return inputDouble * 160934.4;
                } else if (metersButton.isChecked()) {
                    return inputDouble * 1609.344;
                } else {
                    return inputDouble * 1.60934;
                }
            }
        } else {
            if (inchesButton.isChecked()) {
                if (centimetersButton.isChecked()) {
                    return inputDouble * 15;
                } else if (metersButton.isChecked()) {
                    return inputDouble * 15;
                } else {
                    return inputDouble * 15;
                }
            } else if (feetButton.isChecked()) {
                if (centimetersButton.isChecked()) {
                    return inputDouble * 15;
                } else if (metersButton.isChecked()) {
                    return inputDouble * 15;
                } else {
                    return inputDouble * 15;
                }
            } else {
                if (centimetersButton.isChecked()) {
                    return inputDouble * 15;
                } else if (metersButton.isChecked()) {
                    return inputDouble * 15;
                } else {
                    return inputDouble * 15;
                }

            }
        }
    }

    private void displayResult(double result) {
        DecimalFormat myFormatter = new DecimalFormat("0.000000");//Needs to be customized to values/conversions
        String resultString = myFormatter.format(result);
        resultViewText.setText(resultString);
    }
}
//      Find way to restructure private double calculateConversions()

//        Boolean isClicked[] = new Boolean[]{imperialToMetric.isChecked(), metricToImperial.isChecked(),
//                inchesButton.isChecked(), feetButton.isChecked(), milesButton.isChecked(),
//                centimetersButton.isChecked(), metersButton.isChecked(), kilometersButton.isChecked(),
//                !lengthEditText.getText().toString().isEmpty()};
//                for (int i = 0; i < 8; i++)...


//if inches
//inches to Cm
//inches to meters
//inches to km

//if feet
// feet to cm
//feet to met
//feet to km

//if miles
//miles to cm
//miles to met
//mi to km


