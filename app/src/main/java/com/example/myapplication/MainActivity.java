package com.example.myapplication;

import com.example.myapplication.User;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQlite mSQlite;
    private EditText username;
    private EditText userpassword;
    private Button login;
    private Button register;
    private User view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        username = findViewById(R.id.userName);
        userpassword = findViewById(R.id.userpassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this, Register.class);
                startActivity(intent5);
                //finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mSQlite.getAllDATA();
                    boolean userdata = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user= data.get(i);   //可存储账号数量
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            userdata = true;
                            break;
                        } else {
                            userdata = false;
                        }
                    }
                    if (userdata) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        //intent.putExtra("username",name);
                        //intent.putExtra("password",password);  //展示账号密码功能
                        startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mSQlite = new SQlite(MainActivity.this);
    }
}



