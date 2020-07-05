package com.hosnydevtest.login_mvvm_retrofit.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

// TODO: 7/5/2020  
public class Hide_Keyboard {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
