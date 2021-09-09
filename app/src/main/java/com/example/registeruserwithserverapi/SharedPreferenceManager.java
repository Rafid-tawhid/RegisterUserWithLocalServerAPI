package com.example.registeruserwithserverapi;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.registeruserwithserverapi.responses.User;

public class SharedPreferenceManager {
    private static String SHARED_PREF_NAME="RafidTawhid";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        this.context = context;
    }

    void SaveUser(User user)
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("id",user.getId());
        editor.putString("username",user.getUsername());
        editor.putString("email",user.getEmail());
        editor.putBoolean("logged",true);
        editor.apply();
    }
     boolean isLoggedIn()
    {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);

    }

   public User getUser()
   {
       sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

       return new User(sharedPreferences.getInt("id",-1),sharedPreferences.getString("username",null),
               sharedPreferences.getString("email",null));

   }
   void logOut()
   {
       sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       editor=sharedPreferences.edit();
       editor.clear();
       editor.apply();

   }
}
