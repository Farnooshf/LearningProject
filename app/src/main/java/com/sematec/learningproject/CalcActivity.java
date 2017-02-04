package com.sematec.learningproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class CalcActivity extends Activity {

    private int[] numericButtons = {
            R.id.btn_num_zero,
            R.id.btn_num_one,
            R.id.btn_num_two,
            R.id.btn_num_three,
            R.id.btn_num_four,
            R.id.btn_num_five,
            R.id.btn_num_six,
            R.id.btn_num_seven,
            R.id.btn_num_eight,
            R.id.btn_num_nine};
    private int[] operatorButtons = {
            R.id.btn_equal,
            R.id.btn_plus,
            R.id.btn_minus,
            R.id.btn_multiple,
            R.id.btn_divide,
            R.id.btn_clear,
            R.id.btn_dot,
            R.id.btn_sign,
            R.id.btn_percent
    };

    private TextView txt_result;
    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;
    private boolean lastEqual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        txt_result = (TextView) findViewById(R.id.txt_result);
        //txt_result.setText("0");

        setNumericOnClickListener();
        setOperatorOnClickListener();
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button button = (Button) v;
                if (stateError) {
                    txt_result.setText(button.getText());
                    stateError = false;
                } else if (lastEqual && lastNumeric) {
                    txt_result.setText(button.getText());
                    lastEqual = false;
                } else if (lastEqual && !lastNumeric) {
                    txt_result.append(button.getText());
                    lastEqual = false;
                } else {
                    txt_result.append(button.getText());
                }
                lastNumeric = true;
                lastEqual = false;
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    txt_result.append(button.getText());
                    lastNumeric = false;
                    lastDot = false;
                }
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
        findViewById(R.id.btn_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    txt_result.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_result.setText("");
                lastNumeric = false;
                stateError = false;
                lastDot = false;
            }
        });

        findViewById(R.id.btn_percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    Double txtFirst = Double.parseDouble(txt_result.getText().toString());
                    txt_result.setText((txtFirst / 100) + "");
                    lastNumeric = true;
                    stateError = false;
                    lastDot = false;
                }
            }
        });

        findViewById(R.id.btn_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError) {
                    Double txtFirst = Double.parseDouble(txt_result.getText().toString());
                    if (txtFirst >= 0) {
                        txt_result.setText("-" + txtFirst);
                    } else {
                        txt_result.setText(txtFirst + "");
                    }
                    lastNumeric = true;
                    stateError = false;
                    lastDot = false;
                    lastEqual = true;
                }
            }
        });

        findViewById(R.id.btn_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc();

            }
        });
    }


    private void calc() {

        if (lastNumeric && !stateError) {

            String txt = txt_result.getText().toString();

            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                txt_result.setText(Double.toString(result));
                lastDot = true;
                lastEqual = true;
            } catch (ArithmeticException ex) {
                txt_result.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}

