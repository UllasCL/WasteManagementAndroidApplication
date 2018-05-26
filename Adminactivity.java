package com.ullas.firstphase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminactivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminactivity);

        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Login=(Button)findViewById(R.id.btnLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String UserName=( (EditText)findViewById(R.id.etName)).getText().toString();
                String password= ((EditText)findViewById(R.id.etPassword)).getText().toString();
                // Toast.makeText(Login.this,UserName,Toast.LENGTH_LONG).show();
                // Toast.makeText(Login.this,password,Toast.LENGTH_LONG).show();
                //viewAll();
                if(UserName.equals("sit")&&password.equals("101127"))
                {
                    Name.setText(null);
                    Password.setText(null);
                    Intent i=new Intent(adminactivity.this,adminwork.class);
                    startActivity(i);
                }
                else
                {

                    Name.setText(null);
                    Password.setText(null);
                    Toast.makeText(adminactivity.this,"Not an Admin",Toast.LENGTH_LONG).show();

                    //Intent intent=new Intent(Login.this,Login.class);
                }


            }
        });



    }
}
