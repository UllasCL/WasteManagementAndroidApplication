package com.ullas.firstphase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.Console;
/*import static com.ullas.firstphase.Login.UN;
import //static com.ullas.firstphase.Login.PA;*/




public class Registration extends AppCompatActivity
{
    private  static EditText userName,userPassword,userId;
    private Button reg;
    private TextView userLogin;
    public static String un,up;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        setUpUIViews();

        userLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Registration.this,Login.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // Toast.makeText(Registration.this,"entered reg",Toast.LENGTH_LONG).show();
                //startActivity(new Intent(Registration.this,Login.class));
                un= ((EditText) findViewById(R.id.etUserId)).getText().toString();
                up= ((EditText) findViewById(R.id.etUserPassword)).getText().toString();
                //Toast.makeText(Registration.this,un,Toast.LENGTH_LONG).show();
                //Toast.makeText(Registration.this,up,Toast.LENGTH_LONG).show();


               Boolean s= Login.RegInsert();
                   if(s)
                   {
                       Toast.makeText(Registration.this, "Registered", Toast.LENGTH_LONG).show();
                       startActivity(new Intent(Registration.this,Login.class));
                   }
                    else
                    {
                        Toast.makeText(Registration.this,"        User ID exist\n"+"Please try different ID",Toast.LENGTH_LONG).show();
                    }

            }
        });
    }

    private void setUpUIViews()
    {
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword=(EditText)findViewById(R.id.etPassword);
        userId=(EditText)findViewById(R.id.etUserId);
        reg =(Button)findViewById(R.id.btnRegister);
        userLogin =(TextView)findViewById(R.id.tvUserLogin);
    }

  /*  private boolean Validate()
    {
        Boolean result=true;
        String name=userName.getText().toString();
        String password=userPassword.getText().toString();
        String id=userId.getText().toString();
        Toast.makeText(Registration.this, "validate", Toast.LENGTH_SHORT).show();

                   if(name.isEmpty() || password.isEmpty() || id.isEmpty())
                    {

                        startActivity(new Intent(Registration.this,Registration.class));
                        Toast.makeText(Registration.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                        result=false;
                    }
                    else
                    {
                        result=true;
                    }
        return result;
    }*/


    public static String getUsername()
    {
        return un;
    }


    public static String getPassword()
    {
        return up;
    }
}
