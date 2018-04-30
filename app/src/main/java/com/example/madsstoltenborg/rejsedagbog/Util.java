package com.example.madsstoltenborg.rejsedagbog;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Sonnich on 30/04/2018.
 */

public class Util {
    private static int duration;


    public static void showSnackBar(Activity activity, String message, int dur){
        if(dur ==0){
            duration = Snackbar.LENGTH_SHORT;

        }else{
            duration= Snackbar.LENGTH_LONG;
        }
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, duration).show();
    }

}
