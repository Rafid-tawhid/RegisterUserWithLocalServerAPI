package com.example.registeruserwithserverapi.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registeruserwithserverapi.MainActivity;
import com.example.registeruserwithserverapi.R;
import com.example.registeruserwithserverapi.RetrofitClient;
import com.example.registeruserwithserverapi.SharedPreferenceManager;
import com.example.registeruserwithserverapi.responses.LoginResponse;
import com.example.registeruserwithserverapi.responses.UpdatePassResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    EditText etuserName,etuserEmail,currentPass,newPass;
    SharedPreferenceManager sharedPreferenceManager;
    Button updateBtn,updatePass;
    int userId;
    String userEmailId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);



        etuserName=view.findViewById(R.id.userName);
        etuserEmail=view.findViewById(R.id.userEmail);
        currentPass=view.findViewById(R.id.userCurrentPass);
        newPass=view.findViewById(R.id.userNewPass);
        updateBtn=view.findViewById(R.id.btnUpdateAcc);
        updatePass=view.findViewById(R.id.btnUpdatePass);






        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        sharedPreferenceManager=new SharedPreferenceManager(getActivity());
        userId=sharedPreferenceManager.getUser().getId();
        userEmailId=sharedPreferenceManager.getUser().getEmail();

        Log.d("cc",userEmailId);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                updateAcc();
                sharedPreferenceManager.logOut();
            }
        });

       updatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserPass();
            }
        });







    }

    private void updateUserPass() {
        String current=currentPass.getText().toString().trim();
        String newp=newPass.getText().toString().trim();

        if (current.isEmpty())
        {
            currentPass.setError("Please Enter Current Password");
            currentPass.requestFocus();
            return;
        }
        if (newp.isEmpty())
        {
            newPass.setError("Please New Password");
            newPass.requestFocus();
            return;
        }

        Call<UpdatePassResponse> call=RetrofitClient.getInstance().getApi().updateUserPass(current,userEmailId,newp);

        Log.d("ccc",current+userEmailId+newp);
        call.enqueue(new Callback<UpdatePassResponse>() {

            @Override
            public void onResponse(Call<UpdatePassResponse> call, Response<UpdatePassResponse> response) {
                UpdatePassResponse passResponse=response.body();

                Log.d("bb",response.body().getError());
                if (response.isSuccessful())
                {
                    if(passResponse.getError().equals("200"))
                    {
                        Toast.makeText(getActivity(), passResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), passResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<UpdatePassResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Password Update Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void updateAcc() {

        String userName=etuserName.getText().toString().trim();
        String userEmail=etuserEmail.getText().toString().trim();

        if (userName.isEmpty())
        {
            etuserName.setError("Please Enter User Name");
            etuserName.requestFocus();
            return;
        }
        if (userEmail.isEmpty())
        {
            etuserEmail.setError("Please Enter Email");
            etuserEmail.requestFocus();
            return;
        }

        Call<LoginResponse> call= RetrofitClient.getInstance().getApi().updateUserAccount(userId,userName,userEmail);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse updateResponse=response.body();
                if(response.isSuccessful())
                {
                    if (updateResponse.getError().equals("200"))
                    {


                        sharedPreferenceManager.SaveUser(updateResponse.getUser());
                        Toast.makeText(getActivity(), "User Update Succesful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),MainActivity.class));


                    }
                    else
                    {
                        Toast.makeText(getActivity(), ""+updateResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), ""+"Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}