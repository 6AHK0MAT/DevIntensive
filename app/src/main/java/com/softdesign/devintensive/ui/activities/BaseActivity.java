package com.softdesign.devintensive.ui.activities;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.softdesign.devintensive.R;
import com.softdesign.devintensive.utils.ConstantManager;


public class BaseActivity extends AppCompatActivity {
    static final String TAG = ConstantManager.TAG_PREFIX + "Base Activity";
    private ProgressDialog mProgressDialog;



    public void showProgress(){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this, R.style.custom_dialog);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.dialog_splash);
        } else {
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.dialog_splash);
        }
    }


    public void hideProgress(){
        if (mProgressDialog == null){
            if (mProgressDialog.isShowing()){
                mProgressDialog.hide();
            }
        }
    }

    public void showError(String message, Exception exception){
        showToast(message);
        Log.d(TAG, exception.getMessage());
    }

    //Сообщенеи в овале (контекст, сообщение, длительность показа сообщения)
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}