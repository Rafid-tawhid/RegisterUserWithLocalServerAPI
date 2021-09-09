package com.example.registeruserwithserverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registeruserwithserverapi.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText uname,upass;
    Button loginBtn;
    TextView frgtPass,newAccCreate;
    SharedPreferenceManager sharedPreferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=findViewById(R.id.home_ed1);
        upass=findViewById(R.id.home_ed2);
        loginBtn=findViewById(R.id.homebtn);
        frgtPass=findViewById(R.id.homeFrgtPass);
        newAccCreate=findViewById(R.id.homeNewAcc);
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        newAccCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
//                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
        });
    }

    private void userLogin() {


        String usermail=uname.getText().toString();
        String pass=upass.getText().toString();
        if(usermail.isEmpty())
        {
            uname.requestFocus();
            uname.setError("Please Enter Your Name");
            return;
        }
        if(pass.isEmpty())
        {
            upass.requestFocus();
            upass.setError("Please Enter Password");
            return;
        }


        Call<LoginResponse> call=RetrofitClient.getInstance().getApi().login(usermail,pass);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();

                if(response.isSuccessful())
                {
                    if (loginResponse.getError().equals("200"))
                    {
                        sharedPreferenceManager.SaveUser(loginResponse.getUser());
                        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, ""+loginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    }

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, ""+"No Response  " , Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        if(sharedPreferenceManager.isLoggedIn())
        {
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}