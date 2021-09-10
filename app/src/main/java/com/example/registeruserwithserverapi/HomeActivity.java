package com.example.registeruserwithserverapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.registeruserwithserverapi.Fragments.DasgboardFragment;
import com.example.registeruserwithserverapi.Fragments.ProfileFragment;
import com.example.registeruserwithserverapi.Fragments.UsersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    SharedPreferenceManager sharedPreferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferenceManager=new SharedPreferenceManager(getApplicationContext());
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:
                logoutUser();
                break;
            case R.id.deleteAcc:
                deleteAccount();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAccount() {

    }

    private void logoutUser() {
      sharedPreferenceManager.logOut();
        Intent intent=new Intent(HomeActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();

    }
}