package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class TemperatureActivity extends AppCompatActivity {

    private EditText convertFromTempEditText;
    private EditText convertToTempEditText;
    private EditText convertAmountTempEditText;
    Button convertTempButton;
    TextView resultTempViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        findViews();
        setupButtonClickListeners();
    }

    private void findViews() {

        convertFromTempEditText = findViewById(R.id.edit_text_input_convert_from_temp);
        convertToTempEditText = findViewById(R.id.edit_text_input_convert_to_temp);
        convertTempButton = findViewById(R.id.button_convert_temp);
        convertAmountTempEditText = findViewById(R.id.edit_text_input_convert_amount_temp);
        resultTempViewText = findViewById(R.id.text_view_result_temp);
    }

    private void setupButtonClickListeners() {

        convertTempButton.setOnClickListener(v -> {
            boolean inputsChecked = checkMissingInput();

            if (inputsChecked) {

                String alertText = "Please enter a value or a number corresponding to the available indexes above";

                Toast.makeText(TemperatureActivity.this, alertText, Toast.LENGTH_LONG).show();
            } else {

                double convertedTempResult = calculateTempResult();
                displayTempResult(convertedTempResult);
            }
        });
    }

    private boolean checkMissingInput() {
        String convertFromTemp = convertFromTempEditText.getText().toString();
        String convertToTemp = convertToTempEditText.getText().toString();
        int convertFromTempOutOfRange = Integer.parseInt(convertFromTemp);
        int convertToTempOutOfRange = Integer.parseInt(convertToTemp);
        String convertAmount = convertAmountTempEditText.getText().toString();
        boolean indexOutOfRange = true;

        if ((convertFromTempOutOfRange < 6 && convertFromTempOutOfRange > 0) && (convertToTempOutOfRange < 6 && convertToTempOutOfRange > 0)) {
            //returns false only if all variables are entered
            // if even one variable is missing then the function returns true
            return convertFromTemp.isEmpty() || convertToTemp.isEmpty() || convertAmount.isEmpty();
        } else {
            return indexOutOfRange;
        }
    }

    private double calculateTempResult() {
        String stringTempUnitFrom = convertFromTempEditText.getText().toString();
        String stringTempUnitTo = convertToTempEditText.getText().toString();
        String stringConvertTempAmount = convertAmountTempEditText.getText().toString();
        int intTempUnitFrom = Integer.parseInt(stringTempUnitFrom);
        int intTempUnitTo = Integer.parseInt(stringTempUnitTo);
        double doubleTempAmount = Double.parseDouble(stringConvertTempAmount);


        double[][] tempFactorMatrixArray = new double[5][5];

        //tempFactorMatrixArray[FROM(row)][TO(col)]

        tempFactorMatrixArray[0][0] = doubleTempAmount; //K-K
        tempFactorMatrixArray[0][1] = ((doubleTempAmount - 273.15) * 1.8) + 32; // K-F
        tempFactorMatrixArray[0][2] = doubleTempAmount * 1.8; // R
        tempFactorMatrixArray[0][3] = 0.8 * (doubleTempAmount - 273.15); //r
        tempFactorMatrixArray[0][4] = (doubleTempAmount - 273.15); //C

        tempFactorMatrixArray[1][0] = (doubleTempAmount) * 5 / 9 + 273.15; //F-K
        tempFactorMatrixArray[1][1] = doubleTempAmount; //F-F
        tempFactorMatrixArray[1][2] = doubleTempAmount + 459.67; //R
        tempFactorMatrixArray[1][3] = (doubleTempAmount - 32) / 2.25; //r
        tempFactorMatrixArray[1][4] = (doubleTempAmount - 32) / 1.8; //C

        tempFactorMatrixArray[2][0] = doubleTempAmount / 1.8; // R-K
        tempFactorMatrixArray[2][1] = doubleTempAmount - 459.67; // F
        tempFactorMatrixArray[2][2] = doubleTempAmount;// R
        tempFactorMatrixArray[2][3] = (doubleTempAmount - 32 - 459.67) / 2.25;// r
        tempFactorMatrixArray[2][4] = (doubleTempAmount - 32 - 459.67) / 1.8;// C

        tempFactorMatrixArray[3][0] = doubleTempAmount * 1.25 + 273.15; // r-K
        tempFactorMatrixArray[3][1] = doubleTempAmount * 2.25 + 32; // F
        tempFactorMatrixArray[3][2] = doubleTempAmount * 2.25 + 32 + 459.67;// R
        tempFactorMatrixArray[3][3] = doubleTempAmount;// r
        tempFactorMatrixArray[3][4] = doubleTempAmount * 1.25;// C

        tempFactorMatrixArray[4][0] = doubleTempAmount + 273.15; // C-K
        tempFactorMatrixArray[4][1] = doubleTempAmount * 1.8 + 32; // F
        tempFactorMatrixArray[4][2] = doubleTempAmount * 1.8 + 32 + 459.67;// R
        tempFactorMatrixArray[4][3] = doubleTempAmount * 0.8;// r
        tempFactorMatrixArray[4][4] = doubleTempAmount;// C


        for (int row = 0; row < tempFactorMatrixArray.length; row++) {
            for (int col = 0; col < tempFactorMatrixArray[row].length; col++) {
                if ((row == (intTempUnitFrom - 1)) && (col == (intTempUnitTo - 1))) {

                    return tempFactorMatrixArray[intTempUnitFrom - 1][intTempUnitTo - 1];
                }
            }
        }

        return 0.00;
    }

    private void displayTempResult(double tempResult) {

        DecimalFormat myFormatter = new DecimalFormat("0.00");
        String resultTempString = myFormatter.format(tempResult);
        resultTempViewText.setText(resultTempString);
    }
}