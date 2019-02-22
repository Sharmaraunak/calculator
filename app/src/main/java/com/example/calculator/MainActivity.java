package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView calculation, answer;
    //variables needed for calculation
    String sCalculation = "", sAnswer = "", number_one = "", current_operator = "", prev_answer = "";
    Double Result = 0.0, numberOne = 0.0, temp = 0.0;
    //need to reformat answer
    NumberFormat format, longFormat;
    //dot check
    Boolean dot_present = false, number_allow  =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = findViewById(R.id.calculation);
        //set movement to the textView
        calculation.setMovementMethod(new ScrollingMovementMethod());
        calculation.setText("");
        //Initialise answer
        answer = findViewById(R.id.answer);

        //set the answer upto 4 decimal places
        format = new DecimalFormat("#.####");
        //reformat answer if it is too long
        longFormat = new DecimalFormat("0.#E0");

    }


    public void onClickNumber(View v) {
        //number is allowed
        if(number_allow) {
            //find which number is pressed

            Button bn = (Button) v;
            sCalculation += bn.getText();
            number_one += bn.getText();
            numberOne = Double.parseDouble(number_one);

            //switch between buttons
            switch (current_operator) {
                case ""://if current operator is null
                    temp = Result + numberOne;
                    sAnswer = format.format(temp).toString();
                    break;

                case "+"://if current operator is plus
                    temp = Result + numberOne;
                    sAnswer = format.format(temp).toString();
                    break;

                case "-"://if current operator is minus
                    temp = Result - numberOne;
                    sAnswer = format.format(temp).toString();
                    break;

                case "x"://if current operator is multiplication
                    temp = Result * numberOne;
                    sAnswer = format.format(temp).toString();
                    break;

                case "/"://if current operator is null
                    try {
                        //divided by 0 causes exception
                        temp = Result / numberOne;
                        sAnswer = format.format(temp).toString();

                    } catch (Exception e) {
                        sAnswer = e.getMessage();

                    }
                    break;

            }
            updateCalculation();

        }
    }

    public void onClickOperator(View v) {
        Button ob = (Button) v;
        //if sAnswer is null means no calculation needed
        if (sAnswer != "") {
            //check the last char is operator or not
            if (current_operator != "")
            {
                char c = getcharfromLast(sCalculation,2);// 2 is last char because out last char id ""
                if (c == '+' || c == '-' || c == 'x' || c == '/')
                {
                    sCalculation = sCalculation.substring(0,sCalculation.length()-3);

                }
            }
            sCalculation += " " + ob.getText() + " ";
            number_one = "";
            Result = temp;
            current_operator = ob.getText().toString();
            updateCalculation();
            //when operator click dot is not present in number_one
            dot_present = false;
            number_allow = true;
        }

    }

    private char getcharfromLast(String s, int i)
    {
        char c = s.charAt(s.length()-i);
        return c;
    }

    public void onClickClear(View v) {
        sCalculation = "";
        sAnswer = "";
        current_operator = "";
        number_one = "";
        Result =temp;
        numberOne = 0.0;
        temp = 0.0;
        prev_answer = "";
        updateCalculation();
        dot_present = false;
        number_allow = true;

    }

    public void onClickFunction(View v) {

    }

    public void updateCalculation() {

        calculation.setText(sCalculation);
        answer.setText(sAnswer);

    }

    public void onDotClick(View v)
    {
        //create boolean dot _present check if dot is present or not
        if (!dot_present)
        {
            //check length of number_one
            if (number_one.length() == 0)
            {
                number_one = "0.";
                sCalculation = "0.";
                sAnswer = "0.";
                dot_present = true;
                updateCalculation();
            }
            else
            {
                number_one += ".";
                sCalculation += ".";
                sAnswer += ".";
                dot_present  = true;
                updateCalculation();

            }
        }
    }

    public void onClickEqual(View v)
    {
        if (sAnswer != "" && sAnswer != prev_answer)
        {
            sCalculation += "\n= " + sAnswer + "\n------------\n" + sAnswer + " ";
            number_one = "";
            numberOne = 0.0;
            Result = temp;
            prev_answer = sAnswer;
            updateCalculation();
            //no editing of answer
            dot_present = true;
            number_allow = false;
        }
    }

    public void onClickModulo(View view)
    {
        if (sAnswer != "" && getcharfromLast(sCalculation,1) != " ")
        {
            sCalculation += "% ";
            //value of temp will change accordingly to the operator


        }
    }
}
