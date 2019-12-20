package com.faux.workshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSession {

    SharedPreferences pref;
    Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "Reg";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    private static final String KEY_EMAIL = "email";

    private static final String KEY_PASSWORD = "txtPassword";

    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String uPassword, String uEmail) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_EMAIL, uEmail);
        editor.putString(KEY_PASSWORD, uPassword);
        editor.commit();
    }

    public boolean checkLogin() {
        if(!this.isUserLoggedIn()){

            Intent i = new Intent(_context, Login.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);

            return true;
        }
        return false;
    }

    //This can be used to logout the user.
    //Just call the following method from any class.
    public void logoutUser(){

        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, MainActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }


    private boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
