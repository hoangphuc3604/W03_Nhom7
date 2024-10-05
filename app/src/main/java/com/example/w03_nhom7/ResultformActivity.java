package com.example.w03_nhom7;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultformActivity extends AppCompatActivity {
    TextView username, password, birthdate, gender, hobbies;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultform);

        username = findViewById(R.id.usernameSigned);
        password = findViewById(R.id.passwordSigned);
        birthdate = findViewById(R.id.birthdateSigned);
        gender = findViewById(R.id.genderSigned);
        hobbies = findViewById(R.id.hobbiesSigned);
        exitButton = findViewById(R.id.btnExit);

        Intent myCallerIntent = getIntent();
        Bundle myBundle = myCallerIntent.getExtras();

        String strUsername = "Username: " + myBundle.getString("usernameSigned");
        String strPassword = "Password: " + myBundle.getString("passwordSigned").replaceAll(".", "*");
        String strBirthdate = "Birthdate: " + myBundle.getString("birthdateSigned");
        String strGender = "Gender: " + myBundle.getString("genderSigned");
        String strHobbies = "Hobbies: " + myBundle.getString("hobbiesSigned");

        username.setText(strUsername);
        password.setText(strPassword);
        birthdate.setText(strBirthdate);
        gender.setText(strGender);
        hobbies.setText(strHobbies);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAffinity();
                } else {
                    finish();
                }
                System.exit(0);
            }
        });

    }
}
