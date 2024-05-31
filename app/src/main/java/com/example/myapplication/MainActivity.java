package com.example.myapplication;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int randomNumber = 0;
    long startTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        Button btn = (Button) findViewById(R.id.button);
        EditText text = (EditText) findViewById(R.id.editTextText);
        btn.setBackgroundColor(Color.BLACK);
        text.addTextChangedListener(insertRandomNumberTextWatcher);

        FrameLayout touchArea = findViewById(R.id.touch);


        touchArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startTime = System.currentTimeMillis();
                        touchArea.setBackgroundColor(Color.RED);
                        return true;
                    case MotionEvent.ACTION_UP:
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;
                        touchArea.setBackgroundColor(Color.BLACK);

                        float x = event.getX();
                        float y = event.getY();

                        String message = "Pressed for " + duration + " ms\n" +
                                "Coordinates: (" + x + ", " + y + ")";
                        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);

                        toast.show();
                        return true;
                }
                return false;
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        btn.setBackgroundColor(Color.BLUE);
                        return false;
                    case MotionEvent.ACTION_UP:
                        btn.setBackgroundColor(Color.BLACK);
                        //v.performClick();
                        return false;
                }
                return false;
            }
        });


        btn.setOnClickListener(generateRandomNumberOnClick);



    }

    View.OnClickListener generateRandomNumberOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            randomNumber = (int) (Math.random() * 1000);
            TextView rand = (TextView)findViewById(R.id.randomNumberText);
            rand.setText(String.valueOf(randomNumber));
            rand.setVisibility(View.VISIBLE);

        }
    };

    TextWatcher insertRandomNumberTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.toString().length() > 0){
                if (Integer.valueOf(s.toString()) == randomNumber){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
            }
            else{
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}