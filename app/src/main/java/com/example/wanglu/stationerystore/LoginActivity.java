package com.example.wanglu.stationerystore;

import android.content.Intent;

import android.app.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanglu.stationerystore.Navigation.NavigationForClerk;
import com.example.wanglu.stationerystore.Navigation.NavigationForHead;
import com.example.wanglu.stationerystore.Navigation.NavigationForManager;

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
        username.setHint("User Name");
        password.setHint("Password");

        bt_pwd_eye.setOnClickListener((OnClickListener) this);
        login.setOnClickListener((OnClickListener) this);
        
    }

    @Override
    public void onClick(View v) {
        String userName,passWord;
        userName = username.getText().toString();
        passWord=password.getText().toString();


        switch (v.getId()){
            case R.id.login :
                if (userName.equals("MeiTing")&&passWord.equals("Meiting"))//clerk login
                {
                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E012");
                    editor.putString("deptID","STOR");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this, NavigationForClerk.class);
                    startActivity(intent);
                }
                else if (userName.equals("Anthony")&&passWord.equals("Anthony@2"))//clerk login
                {

                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E014");
                    editor.putString("deptID","STOR");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this, NavigationForClerk.class);
                    startActivity(intent);
                }
                else if (userName.equals("clerk3")){

                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E014");
                    editor.putString("deptID","STOR");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this, NavigationForClerk.class);
                    startActivity(intent);
                }

                else if (userName.equals("head")){

                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E003");
                    editor.putString("deptID","CPSC");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForHead.class);
                    startActivity(intent);
                }
                else if (userName.equals("David")&&passWord.equals("DavidLogic@1"))//chemistry head
                {

                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E008");
                    editor.putString("deptID","CHEM");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForHead.class);
                    startActivity(intent);
                }
                else if (userName.equals("Esther")&&passWord.equals("Esther@21"))//Architecture head
                {

                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E010");
                    editor.putString("deptID","ARCH");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForHead.class);
                    startActivity(intent);
                }
                else if (userName.equals("manager")){
                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("empID","E011");
                    editor.putString("deptID","STOR");
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(this,NavigationForManager.class);
                    startActivity(intent);
                }
                else  {
                    Toast.makeText(this, "Please use correct user name and password", Toast.LENGTH_SHORT).show();
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

