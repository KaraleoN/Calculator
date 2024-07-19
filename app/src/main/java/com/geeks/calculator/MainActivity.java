package com.geeks.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private StringBuilder currentNumber = new StringBuilder();
    private double firstValue = Double.NaN;
    private double secondValue = Double.NaN;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);
    }

    // Обработчик для кнопок с цифрами
    public void numberOnClick(View view) {
        String digit = ((TextView) view).getText().toString();
        currentNumber.append(digit);
        textViewResult.setText(currentNumber.toString());
    }

    // Обработчик для кнопок операций (+, -, *, /)
    public void operationOnClick(View view) {
        String op = ((TextView) view).getText().toString();
        if (currentNumber.length() > 0) {
            if (!Double.isNaN(firstValue)) {
                secondValue = Double.parseDouble(currentNumber.toString());
                calculateResult();
            } else {
                firstValue = Double.parseDouble(currentNumber.toString());
            }
            currentNumber.setLength(0);
            operator = op;
        }
    }

    // Обработчик для кнопки точки
    public void dotOnClick(View view) {
        if (!currentNumber.toString().contains(".")) {
            currentNumber.append(".");
            textViewResult.setText(currentNumber.toString());
        }
    }

    // Обработчик для кнопки равно
    public void calculateResult() {
        if (!Double.isNaN(firstValue) && !Double.isNaN(secondValue)) {
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "×":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) {
                        result = firstValue / secondValue;
                    } else {
                        // Обработка деления на ноль
                        result = Double.NaN;
                    }
                    break;
            }
            textViewResult.setText(String.valueOf(result));
            firstValue = result;
            secondValue = Double.NaN;
            currentNumber.setLength(0);
        }
    }

    // Обработчик для кнопки очистки
    public void clearOnClick(View view) {
        currentNumber.setLength(0);
        firstValue = Double.NaN;
        secondValue = Double.NaN;
        operator = "";
        textViewResult.setText("0");
    }
}