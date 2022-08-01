package com.example.baseconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView binary = findViewById(R.id.binEdtView);
        TextView octal = findViewById(R.id.octalEdtView);
        TextView decimal = findViewById(R.id.decimalEdtView);
        TextView hexa = findViewById(R.id.hexaEdtView);

        Button convertBtn = (Button) findViewById(R.id.convertBtn);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberConversion number = new NumberConversion();
                if (binary.getText().toString() != null) {
                    number.setBinary(binary.getText().toString());

                    editAllTextView(octal, number.getOctal(), decimal, number.getDecimal(), hexa, number.getHexadecimal());
                }
                if (octal.getText().toString() != null) {
                    number.setOctal(octal.getText().toString());

                    editAllTextView(binary, number.getBinary(), decimal, number.getDecimal(), hexa, number.getHexadecimal());
                }
                if (decimal.getText().toString() != null) {
                    number.setDecimal(decimal.getText().toString());

                    editAllTextView(binary, number.getBinary(), octal, number.getOctal(), hexa, number.getHexadecimal());
                }
                if (hexa.getText().toString() != null){
                    number.setHexadecimal(hexa.getText().toString());

                    editAllTextView(binary, number.getBinary(), octal, number.getOctal(), decimal, number.getDecimal());
                }
            }
        });

        Button resetBtn = (Button) findViewById(R.id.resetBtn);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binary.setText(null);
                octal.setText(null);
                decimal.setText(null);
                hexa.setText(null);
            }
        });
    }

    public void editAllTextView(TextView textView1, String content1, TextView textView2, String content2, TextView textView3, String content3) {
        textView1.setText(content1);
        textView2.setText(content2);
        textView3.setText(content3);
    }
}