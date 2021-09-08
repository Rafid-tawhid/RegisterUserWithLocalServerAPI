package com.example.registeruserwithserverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.registeruserwithserverapi.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText uname,upass,uemail,uphone;
    Button newAccBtn;
    ImageView backBtn,profilePhoto;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uname=findViewById(R.id.reg_ed1);
        uemail=findViewById(R.id.reg_ed2);
        uphone=findViewById(R.id.reg_ed3);
        upass=findViewById(R.id.reg_ed4);
        newAccBtn=findViewById(R.id.regbtn);
        profilePhoto=findViewById(R.id.profileImage);
        backBtn=findViewById(R.id.bactBtn);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        newAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();


            }
        });



    }

    private void registerUser() {
        String username=uname.getText().toString();
        String useremail=uemail.getText().toString();
        String userpass=upass.getText().toString();

        if(username.isEmpty())
        {
            uname.requestFocus();
            uname.setError("Please Enter Your Name");
            return;
        }
        if(useremail.isEmpty())
        {
            uemail.requestFocus();
            uemail.setError("Please Enter Your E-mail");
            return;
        }
        if(userpass.isEmpty())
        {
            upass.requestFocus();
            upass.setError("Give a Password");
            return;
        }

        Call<RegisterResponse> call=RetrofitClient.getInstance().getApi().register(username,useremail,userpass);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                RegisterResponse registerResponse=response.body();
                if(response.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, ""+registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, ""+registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}