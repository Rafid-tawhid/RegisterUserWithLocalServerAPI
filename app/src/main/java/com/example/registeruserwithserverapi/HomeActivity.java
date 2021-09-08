package com.example.registeruserwithserverapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.registeruserwithserverapi.Fragments.DasgboardFragment;
import com.example.registeruserwithserverapi.Fragments.ProfileFragment;
import com.example.registeruserwithserverapi.Fragments.UsersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        bottomNavigationView=findViewById(R.id.bottomNav);

        loadFragment(new DasgboardFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


               Fragment fragment=null;

               switch (item.getItemId())
               {
                   case R.id.dashboard:
                       fragment=new DasgboardFragment();
                       break;
                   case R.id.users:
                       fragment=new UsersFragment();
                       break;
                   case R.id.profile:
                       fragment=new ProfileFragment();
                       break;
               }
               if(fragment!=null)
               {
                   loadFragment(fragment);
               }


                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelly,fragment).commit();
    }


}