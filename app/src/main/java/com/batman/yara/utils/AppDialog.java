package com.batman.yara.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.batman.yara.R;

public class AppDialog {

    private Dialog dialog;
    private Context context;

    public AppDialog(Context context) {
        this.context = context;
    }

    public void loadingDialog() {
        if (dialog != null)
            if (dialog.isShowing())
                return;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }

    public void AppDialogCancel() {
        if (dialog != null)
            dialog.cancel();
    }

    public Dialog getDialog() {
        return dialog;
    }
}
