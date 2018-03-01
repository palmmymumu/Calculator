package me.palmz.calc.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvHistory;
    private TextView tvResult;

    private double   sum;
    private boolean  isPristine;
    private char     lastOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHistory   = (TextView) findViewById(R.id.tvHistory);
        tvResult    = (TextView) findViewById(R.id.tvResult);
        sum         = 0;
        isPristine    = true;
        lastOperator    = '+';
        tvHistory.setText("");
        tvResult.setText("0");
    }

    public void btnClick(View view) {
        String btn = ((Button) view).getText().toString();
        String input = tvResult.getText().toString();
        String history = tvHistory.getText().toString();
        switch (btn) {
            case "C":
                sum = 0;
                lastOperator = '+';
                tvHistory.setText("");
                tvResult.setText("0");
                isPristine = true;
                break;
            case "CE":
                tvResult.setText("0");
                isPristine = true;
                break;
            case "Back":
                if (input.length() == 1)
                    tvResult.setText("0");
                else
                    tvResult.setText(input.substring(0, input.length() - 1));
                isPristine = true;
                break;
            case "0":
                if (!isPristine)
                    input = "0";
                else if (input != "0")
                    input += "0";
                tvResult.setText(input);
                isPristine = true;
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                // Log.d("error", "btnClick: '" + (input == "0" ? "true" : "false") + "' " + input);
                if (input == "0" || !isPristine) // Clear if input equals to 0 or it isn't an old value
                    input = "";
                input += btn;
                tvResult.setText(input);
                isPristine = true;
                break;
            case ".":
                if (input.indexOf(".") == -1)
                    input += ".";
                tvResult.setText(input);
                isPristine = true;
                break;
            case "+":
                if (!isPristine) {
                    lastOperator = '+';
                    tvHistory.setText(history.substring(0, history.length() - 2) + lastOperator + " ");
                } else {
                    doOperator(input);
                    lastOperator = '+';
                    tvHistory.setText(history + trailingZero(Double.parseDouble(input)) + " " + lastOperator + " ");
                    tvResult.setText(trailingZero(sum));
                    isPristine = false;
                }
                break;
            case "-":
                if (!isPristine) {
                    lastOperator = '-';
                    tvHistory.setText(history.substring(0, history.length() - 2) + lastOperator + " ");
                } else {
                    doOperator(input);
                    lastOperator = '-';
                    tvHistory.setText(history + trailingZero(Double.parseDouble(input)) + " " + lastOperator + " ");
                    tvResult.setText(trailingZero(sum));
                    isPristine = false;
                }
                break;
            case "×":
                if (!isPristine) {
                    lastOperator = '×';
                    tvHistory.setText(history.substring(0, history.length() - 2) + lastOperator + " ");
                } else {
                    doOperator(input);
                    lastOperator = '×';
                    tvHistory.setText(history + trailingZero(Double.parseDouble(input)) + " " + lastOperator + " ");
                    tvResult.setText(trailingZero(sum));
                    isPristine = false;
                }
                break;
            case "÷":
                if (!isPristine) {
                    lastOperator = '÷';
                    tvHistory.setText(history.substring(0, history.length() - 2) + lastOperator + " ");
                } else {
                    doOperator(input);
                    lastOperator = '÷';
                    tvHistory.setText(history + trailingZero(Double.parseDouble(input)) + " " + lastOperator + " ");
                    tvResult.setText(trailingZero(sum));
                    isPristine = false;
                }
                break;
            case "=":
                doOperator(input);
                tvHistory.setText("");
                tvResult.setText(trailingZero(sum));
                sum = 0;
                lastOperator = '+';
                isPristine = true;
                break;
            case "±":
                tvResult.setText(trailingZero(Double.parseDouble(input) * -1));
                isPristine = true;
                break;
            default:
                break;
        }

    }

    public String trailingZero(Double input) {
        String s = String.valueOf(input);
        return s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
        // https://stackoverflow.com/questions/14984664/remove-trailing-zero-in-java
    }

    public void doOperator(String input) {
        Double tmp = Double.parseDouble(input);
        switch (lastOperator) {
            case '+':
                sum += tmp;
                break;
            case '-':
                sum -= tmp;
                break;
            case '×':
                sum *= tmp;
                break;
            case '÷':
                sum /= tmp;
                break;
        }
    }
}
