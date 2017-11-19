package com.armatour.aramtour.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import com.armatour.aramtour.R;

public class ViewUtil {

    public static ProgressDialog createProgressDialog(Context context){
        ProgressDialog dialog = new ProgressDialog(context, R.style.full_screen_dialog){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.dialog_loading);
                getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            }
        };

        dialog.setCancelable(true);
        return dialog;
    }
}
