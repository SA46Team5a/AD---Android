package com.example.wanglu.stationerystore;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity  implements OnClickListener{


    private EditText username, password;
    private Button bt_pwd_eye;
    private Button login;
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        bt_pwd_eye = (Button) findViewById(R.id.bt_pwd_eye);
        login = (Button) findViewById(R.id.login);

        bt_pwd_eye.setOnClickListener((OnClickListener) this);
        login.setOnClickListener((OnClickListener) this);

        //initWatcher();
        //username.addTextChangedListener(username_watch);

    }

    @Override
    public void onClick(View v) {
        String string;
        string = username.getText().toString();
        switch (v.getId()){
            case R.id.login :
                if (string.equals("clerk")){
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForClerk.class);
                    startActivity(intent);
                }
                else if (string.equals("head")){
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForHead.class);
                    startActivity(intent);
                }
                else if (string.equals("manager")){
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForManager.class);
                    startActivity(intent);
                }
                else  {
                    Toast.makeText(this, "Please use correct username", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent();
//                    intent.setClass(this,MainActivity.class);
//                    startActivity(intent);
                break;
                }
            case  R.id.bt_pwd_eye:
                if (password.getInputType()==(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    bt_pwd_eye.setBackgroundResource(R.drawable.password_open);
                    password.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                }
                else {
                    bt_pwd_eye.setBackgroundResource(R.drawable.password_close);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                password.setSelection(password.getText().toString().length());
                break;
            }
        }

//    private void startActivity(Class<MainActivity> mainActivityClass) {
//        Intent intent = new Intent();
//        intent.setClass(this,NavigationForClerk.class);
//        startActivity(intent);
//    }
}

