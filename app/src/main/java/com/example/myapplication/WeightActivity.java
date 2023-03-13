package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class WeightActivity extends AppCompatActivity {

    private EditText convertFromEditText;
    private EditText convertToEditText;
    private EditText convertAmountEditText;
    Button convertWeightButton;
    TextView resultViewTextWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        findViews();
        setupButtonClickListeners();
    }

    private void findViews() {
        convertFromEditText = findViewById(R.id.edit_text_input_convert_from);
        convertToEditText = findViewById(R.id.edit_text_input_convert_to);
        convertWeightButton = findViewById(R.id.button_convert_weight);
        convertAmountEditText = findViewById(R.id.edit_text_input_convert_amount);
        resultViewTextWeight = findViewById(R.id.text_view_result_weight);


    }

    private void setupButtonClickListeners() {

        convertWeightButton.setOnClickListener(v -> {
            boolean inputsChecked = checkMissingInput();

            if (!inputsChecked) {

                String alertText = "Please enter a value or a number corresponding to the available indexes above";

                Toast.makeText(WeightActivity.this, alertText, Toast.LENGTH_LONG).show();
            } else {

                double convertedResult = calculateResult();
                displayResult(convertedResult);
            }

        });
    }

    private boolean checkMissingInput() {
        String convertFrom = convertFromEditText.getText().toString();
        String convertTo = convertToEditText.getText().toString();
        //boolean indexOutOfRange; <- do a separate function below/above (check what works)
        //if (calculateConversions() == 1.2345){


        return !convertFrom.isEmpty() || !convertTo.isEmpty();
    }

    private double calculateConversions() {

        String stringUnitFrom = convertFromEditText.getText().toString();
        String stringUnitTo = convertToEditText.getText().toString();
        String stringConvertAmount = convertAmountEditText.getText().toString();
        int intUnitFrom = Integer.parseInt(stringUnitFrom);
        int intUnitTo = Integer.parseInt(stringUnitTo);
        //double doubleConvertAmount = Double.parseDouble(stringConvertAmount);

        //FROM (row->[x][]):
        //0-oz
        //1-lb
        //2-us tons
        //3-uk tons
        //4-stones
        //5-mg
        //6-mcg
        //7-g
        //8-kg

        //(col->[][y])  oz | lb | usT  | ukT | stone | mg | mcg | g | kg |
        //  TO:         0    1    2      3       4      5    6    7    8

        //factorMatrixArray[FROM][TO]

        double[][] factorMatrixArray = new double[9][9];

        factorMatrixArray[0][0] = 1; //oz-oz
        factorMatrixArray[0][1] = 0.0625; //oz-lbs
        factorMatrixArray[0][2] = 0.00003125; // tons us
        factorMatrixArray[0][3] = 0.00002835; //ton uk
        factorMatrixArray[0][4] = 0.0045; //stones
        factorMatrixArray[0][5] = 28349.52; //mg
        factorMatrixArray[0][6] = 28349523.125; //mcg
        factorMatrixArray[0][7] = 28.349523125; //g
        factorMatrixArray[0][8] = 0.0283495231; //kg

        factorMatrixArray[1][0] = 16; //lbs-oz
        factorMatrixArray[1][1] = 1; //lbs-lbs
        factorMatrixArray[1][2] = 0.0005; //tons us
        factorMatrixArray[1][3] = 0.0004464286; //tons uk
        factorMatrixArray[1][4] = 0.0714286; //stones
        factorMatrixArray[1][5] = 453592; //mg
        factorMatrixArray[1][6] = 453600000.00; //mcg
        factorMatrixArray[1][7] = 453.592; //g
        factorMatrixArray[1][8] = 0.453592; //kg

        factorMatrixArray[2][0] = 32000; // ton us-oz
        factorMatrixArray[2][1] = 2000; // lb
        factorMatrixArray[2][2] = 1;// ton us
        factorMatrixArray[2][3] = 0.892857;// ton uk
        factorMatrixArray[2][4] = 142.857;// stones
        factorMatrixArray[2][5] = 907200000.00;// mg
        factorMatrixArray[2][6] = 907200000000.00;// mcg
        factorMatrixArray[2][7] = 907185;// g
        factorMatrixArray[2][8] = 907.185;// kg

        factorMatrixArray[3][0] = 35840; // ton uk-oz
        factorMatrixArray[3][1] = 2240; // lb
        factorMatrixArray[3][2] = 1.12;// ton us
        factorMatrixArray[3][3] = 1;// ton uk
        factorMatrixArray[3][4] = 160;// stones
        factorMatrixArray[3][5] = 1016000000.00;// mg
        factorMatrixArray[3][6] = 1016000000000.00;// mcg
        factorMatrixArray[3][7] = 1016000.00;// g
        factorMatrixArray[3][8] = 1016.05;// kg

        factorMatrixArray[4][0] = 224; // stones-oz
        factorMatrixArray[4][1] = 14; // lb
        factorMatrixArray[4][2] = 0.007;// ton us
        factorMatrixArray[4][3] = 0.00625;// ton uk
        factorMatrixArray[4][4] = 1;// stones
        factorMatrixArray[4][5] = 6350000.00;// mg
        factorMatrixArray[4][6] = 6350000000.00;// mcg
        factorMatrixArray[4][7] = 6350.29;// g
        factorMatrixArray[4][8] = 6.35029;// kg

        factorMatrixArray[5][0] = 0.000035274; // mg-oz
        factorMatrixArray[5][1] = 0.0000022046; // lb
        factorMatrixArray[5][2] = 0.0000000011023;// ton us
        factorMatrixArray[5][3] = 0.00000000098421;// ton uk
        factorMatrixArray[5][4] = 0.00000015747;// stones
        factorMatrixArray[5][5] = 1;// mg
        factorMatrixArray[5][6] = 1000;// mcg
        factorMatrixArray[5][7] = 0.001;// g
        factorMatrixArray[5][8] = 0.000001;// kg

        factorMatrixArray[6][0] = 0.000000035274; // mcg-oz
        factorMatrixArray[6][1] = 0.0000000022046; // lb
        factorMatrixArray[6][2] = 0.0000000000011023;// ton us
        factorMatrixArray[6][3] = 0.00000000000098421;// ton uk
        factorMatrixArray[6][4] = 0.00000000015747;// stones
        factorMatrixArray[6][5] = 0.001;// mg
        factorMatrixArray[6][6] = 1;// mcg
        factorMatrixArray[6][7] = 0.000001;// g
        factorMatrixArray[6][8] = 0.000000001;// kg

        factorMatrixArray[7][0] = 0.035274; // g-oz
        factorMatrixArray[7][1] = 0.00220462; // lb
        factorMatrixArray[7][2] = 0.0000011023;// ton us
        factorMatrixArray[7][3] = 0.00000098421;// ton uk
        factorMatrixArray[7][4] = 0.000157473;// stones
        factorMatrixArray[7][5] = 1000;// mg
        factorMatrixArray[7][6] = 1000000;// mcg
        factorMatrixArray[7][7] = 1;// g
        factorMatrixArray[7][8] = 0.001;// kg

        factorMatrixArray[8][0] = 35.274; // kg-oz
        factorMatrixArray[8][1] = 2.20462; // lb
        factorMatrixArray[8][2] = 0.00110231;// ton us
        factorMatrixArray[8][3] = 0.000984207;// ton uk
        factorMatrixArray[8][4] = 0.157473;// stones
        factorMatrixArray[8][5] = 1000000;// mg
        factorMatrixArray[8][6] = 1000000000;// mcg
        factorMatrixArray[8][7] = 1000;// g
        factorMatrixArray[8][8] = 1;// kg

        for (int row = 0; row < factorMatrixArray.length; row++) {
            for (int col = 0; col < factorMatrixArray[row].length; col++) {
                if ((row == (intUnitFrom - 1)) && (col == (intUnitTo - 1))) {

                    return factorMatrixArray[intUnitFrom - 1][intUnitTo - 1];
                }

            }
        }
        return 1.2345;

    }

    private double calculateResult() {

        String stringUnitAmount = convertAmountEditText.getText().toString();
        double doubleUnitAmount = Double.parseDouble(stringUnitAmount);
        double conversionFactor = calculateConversions();

        return doubleUnitAmount * conversionFactor;
    }

    private void displayResult(double result) {
        DecimalFormat myFormatter = new DecimalFormat("0.0000");//Needs to be customized to values/conversions
        String resultString = myFormatter.format(result);
        resultViewTextWeight.setText(resultString);
    }

}
/*


 */

