package com.example.w03_nhom7;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    Context context;
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

                //Create a birthdateEdit picker with current birthdateEdit selectBirthdateBtned
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        //Listener/Callback after a birthdateEdit is chosen
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month++;
                                String birthdateEditStr = dayOfMonth + "/" + month + "/" + year;
                                birthdateEdit.setText(birthdateEditStr);
                            }
                        }, y, m, d);
                datePickerDialog.show();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String retype = retypeEdit.getText().toString();
                String birthdate = birthdateEdit.getText().toString();
                String gender = genderOptions.getCheckedRadioButtonId() == R.id.optionMale ? "Male" : "Female";
                String hobbies = "";

                context = getApplicationContext();
                if (username.isEmpty()) {
                    Toast.makeText(context, "Enter your username, please!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(context, "Enter your password, please!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(retype)) {
                    Toast.makeText(context,"Retype incorrectly! Try again!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (birthdate.isEmpty()) {
                    Toast.makeText(context, "Enter your birthdate, please!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isDateFormat(birthdate)) {
                    Toast.makeText(context, "Invalid birthdate. Select again!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!maleOption.isChecked() && !femaleOption.isChecked()) {
                    Toast.makeText(context, "Select your gender, please!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (tennisCheck.isChecked()) {
                    hobbies += "Tennis";
                }
                if (futbalCheck.isChecked()) {
                    if (!hobbies.isEmpty()){
                        hobbies += ", ";
                    }
                    hobbies += "Futbal";
                }
                if (othersCheck.isChecked()) {
                    if (!hobbies.isEmpty()) {
                        hobbies += ", ";
                    }
                    hobbies += "Others";
                }

                Intent myIntentA1A2 = new Intent(MainActivity.this, ResultformActivity.class);
                Bundle myBundle1 = new Bundle();

                myBundle1.putString("usernameSigned", username);
                myBundle1.putString("passwordSigned", password);
                myBundle1.putString("birthdateSigned", birthdate);
                myBundle1.putString("genderSigned", gender);
                myBundle1.putString("hobbiesSigned", hobbies);

                myIntentA1A2.putExtras(myBundle1);

                startActivity(myIntentA1A2);

            }
        });

    }

    private boolean isDateFormat(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            sdf.parse(strDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}