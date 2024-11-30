package com.example.electricitycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputUnit, inputRebate;
    private TextView displayBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputUnit = findViewById(R.id.inputUnit);
        inputRebate = findViewById(R.id.inputRebate);
        displayBill = findViewById(R.id.displayBill);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        Button btnDelete = findViewById(R.id.btnDelete);
        ImageButton imageButtonInfo = findViewById(R.id.imageButtonInfo);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });


        imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateBill() {
        try {

            if (inputUnit.getText().toString().trim().isEmpty()) {
                displayBill.setText("Error, Please enter the number of units.");
                return;
            }
            if (inputRebate.getText().toString().trim().isEmpty()) {
                displayBill.setText("Error, Please enter the rebate percentage.");
                return;
            }


            double units = Double.parseDouble(inputUnit.getText().toString().trim());
            double rebate = Double.parseDouble(inputRebate.getText().toString().trim());


            if (units <= 0) {
                displayBill.setText("Error,Please enter a valid number of units greater than 0.");
                return;
            }

            if (rebate < 0 || rebate > 5) {
                displayBill.setText("Error, Rebate must be valid between 0% to 5%.");
                return;
            }


            double totalCharges = calculateCharges(units);
            double finalCost = totalCharges - (totalCharges * rebate / 100);


            displayBill.setText(String.format("Your bill is: RM %.2f", finalCost));

        } catch (NumberFormatException e) {
            displayBill.setText("Please enter valid numbers for units and rebate.");
        } catch (Exception e) {
            displayBill.setText("An unexpected error occurred. Please try again.");
        }
    }

    private double calculateCharges(double units) {
        double totalCharges = 0;


        if (units <= 200) {
            totalCharges = units * 0.218;
        } else if (units <= 300) {
            totalCharges = 200 * 0.218 + (units - 200) * 0.334;
        } else if (units <= 600) {
            totalCharges = 200 * 0.218 + 100 * 0.334 + (units - 300) * 0.516;
        } else {
            totalCharges = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (units - 600) * 0.546;
        }

        return totalCharges;
    }

    private void clearFields() {

        inputUnit.setText("");
        inputRebate.setText("");
        displayBill.setText("Your bill will be displayed here.");
    }



}
