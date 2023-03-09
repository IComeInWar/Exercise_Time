package com.example.exercisetime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CommonDialogs {
    public static void errorDialogue(String message, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(message)
                .setNeutralButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }
    public static boolean alphanumericDialogue(String text, String descriptor, Context context){
        if (!text.matches("^[A-Za-z0-9 ]+$")) {
            errorDialogue("The " + descriptor + " must be alphanumeric!", context);
            return false;
        }
        return true;
    }

    public static void confirmDialogue(String message, DialogInterface.OnClickListener listener, Context context){
        new AlertDialog.Builder(context)
                .setTitle("Confirm")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, listener)
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

}
