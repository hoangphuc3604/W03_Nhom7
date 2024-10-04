package com.example.w03_nhom7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username, password, retype, date;
    RadioButton male, female;
    RadioGroup gender;
    CheckBox tennis, futbal, others;
    Button reset, sign, select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        retype = findViewById(R.id.editRetype);
        date = findViewById(R.id.editDate);

        gender = findViewById(R.id.gender);
        male = findViewById(R.id.genderMale);
        female = findViewById(R.id.genderFemale);

        tennis = findViewById(R.id.checkTennis);
        futbal = findViewById(R.id.checkFutbal);
        others = findViewById(R.id.checkOthers);

        reset = findViewById(R.id.btnReset);
        sign = findViewById(R.id.btnSign);
        select = findViewById(R.id.btnSelect);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
                retype.setText("");
                date.setText("");

                gender.clearCheck();

                tennis.setChecked(false);
                futbal.setChecked(false);
                others.setChecked(false);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }
}