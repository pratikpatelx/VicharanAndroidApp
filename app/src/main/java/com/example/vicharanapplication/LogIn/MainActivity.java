package com.example.vicharanapplication.LogIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vicharanapplication.APIController.APIClient;
import com.example.vicharanapplication.Presentation.HomeActivity;
import com.example.vicharanapplication.Presentation.HomeFragment;
import com.example.vicharanapplication.R;
import com.example.vicharanapplication.Requests.LogInRequest;
import com.example.vicharanapplication.Responses.LogInResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //stores the email and password fields
    public TextInputEditText email, password;
    //The log in button
    public Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set our view for log in activity
        setContentView(R.layout.activity_log_in);

        //get the email text field contents
        email = findViewById(R.id.login_email);
        //get the password text field contents
        password = findViewById(R.id.login_password);
        //get the button
        loginBtn = findViewById(R.id.btn_login);

        //set a click listener on the log in button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store the username
                boolean usernameField = TextUtils.isEmpty(email.getText().toString());
                //store the password
                boolean passwordField = TextUtils.isEmpty(password.getText().toString());
                //if user and password is empty show a message to input both details
                if (usernameField || passwordField)
                {
                    Toast.makeText(MainActivity.this, "Email or Password is Required", Toast.LENGTH_LONG).show();
                }
                //otherwise we log in the user
                else
                {
                    loginUser();
                }
            }
        });

    }

    private void loginUser() {
        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setUsername(email.getText().toString());
        logInRequest.setPassword(password.getText().toString());

        Call<LogInResponse> logInResponseCall = APIClient.getInterface().userLogin(logInRequest);
        logInResponseCall.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                    LogInResponse loginResponse = response.body();
                    boolean JWTToken = loginResponse.getResponse().startsWith("Error");
                    if (JWTToken == false)
                    {
                        openHomeActivity();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}