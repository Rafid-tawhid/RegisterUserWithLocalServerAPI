package com.example.registeruserwithserverapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.registeruserwithserverapi.R;
import com.example.registeruserwithserverapi.SharedPreferenceManager;


public class DasgboardFragment extends Fragment {

    TextView name,email;

    SharedPreferenceManager sharedPreferenceManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dasgboard, container, false);

        name=view.findViewById(R.id.etname);
        email=view.findViewById(R.id.etmail);
        sharedPreferenceManager=new SharedPreferenceManager(getActivity());
        name.setText("Hey !"+sharedPreferenceManager.getUser().getUsername());
        email.setText(sharedPreferenceManager.getUser().getEmail());

        return view;
    }
}