package com.example.w03_nhom7;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText usernameEdit, passwordEdit, retypeEdit, birthdateEdit;
    RadioButton maleOption, femaleOption;
    RadioGroup genderOptions;
    CheckBox tennisCheck, futbalCheck, othersCheck;
    Button resetButton, signUpButton, selectBirthdateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        usernameEdit = findViewById(R.id.editUsername);
        passwordEdit = findViewById(R.id.editPassword);
        retypeEdit = findViewById(R.id.editRetype);
        birthdateEdit = findViewById(R.id.editBirthdate);

        genderOptions = findViewById(R.id.optionsGender);
        maleOption = findViewById(R.id.optionMale);
        femaleOption = findViewById(R.id.optionFemale);

        tennisCheck = findViewById(R.id.checkTennis);
        futbalCheck = findViewById(R.id.checkFutbal);
        othersCheck = findViewById(R.id.checkOthers);

        resetButton = findViewById(R.id.btnReset);
        signUpButton = findViewById(R.id.btnSignUp);
        selectBirthdateButton = findViewById(R.id.btnBirthdateSelect);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEdit.setText("");
                passwordEdit.setText("");
                retypeEdit.setText("");
                birthdateEdit.setText("");

                genderOptions.clearCheck();

                tennisCheck.setChecked(false);
                futbalCheck.setChecked(false);
                othersCheck.setChecked(false);
            }
        });

        selectBirthdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get current birthdateEdit
                final Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);

                //Create a DatePickerDialog picker with current date selected
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        //Listener/Callback after a birthdateEdit is chosen
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                String birthdateEditStr = dayOfMonth + "/" + month + "/" + year;
                                birthdateEdit.setText(birthdateEditStr);
                            }
                        }, y, m, d);
                datePickerDialog.show();
            }
        });
    }
}