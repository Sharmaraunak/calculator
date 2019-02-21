package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView calculation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = findViewById(R.id.calculation);
        //set movement to the textView
        calculation.setMovementMethod(new ScrollingMovementMethod());
        calculation.setText("");
    }
}
