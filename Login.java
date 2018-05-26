package com.ullas.firstphase;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class Login extends AppCompatActivity
{

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login,admin;
    private int counter=5;
    private TextView UserRegistraion;
    public static Databasehelper myDb;

    public static String UN,PA;

    public  static Complaints cpmanual1;


    // public static String UsNa;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpmanual1=new Complaints(this);

        myDb = new Databasehelper(this);
        admin=(Button)findViewById(R.id.btnadmin);
        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etPassword);
        Info=(TextView)findViewById(R.id.tvInfo);
        Login=(Button)findViewById(R.id.btnLogin);
        UserRegistraion=(TextView)findViewById(R.id.tvRegister);

      //  UsNa=((EditText)findViewById(R.id.etName)).getText().toString();


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,adminactivity.class);
                startActivity(i);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String UserName=( (EditText)findViewById(R.id.etName)).getText().toString();
                String password= ((EditText)findViewById(R.id.etPassword)).getText().toString();
                // Toast.makeText(Login.this,UserName,Toast.LENGTH_LONG).show();
               // Toast.makeText(Login.this,password,Toast.LENGTH_LONG).show();
               //viewAll();
               if(Validatenull(UserName,password))
                   validate(UserName,password);
                else
                {
                        counter--;
                        Info.setText("Num of attempts remaining :"+ String.valueOf(counter));
                        Name.setText(null);
                        Password.setText(null);
                        if(counter==0)
                        {
                            Login.setEnabled(false);
                        }


                    //Intent intent=new Intent(Login.this,Login.class);
                }


            }
        });

        UserRegistraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Login.this,Registration.class);
                intent.putExtra("databaseobject",myDb);
                startActivity(intent);

            }
        });

    }
    private void validate(String username,String password)
    {
        String us,pa;
        boolean flag=false;
      //  viewAll();
      //  Toast.makeText(this, "entered validate", Toast.LENGTH_LONG).show();

        Cursor res = myDb.getAllData();

        while (res.moveToNext())
        {
            us=res.getString(0);
            pa=res.getString(1);
           // Toast.makeText(this, us, Toast.LENGTH_LONG).show();
           // Toast.makeText(this, pa, Toast.LENGTH_LONG).show();

            if(us.equals(username)&&pa.equals(password))
            {
                Toast.makeText(this, "Successfull Login", Toast.LENGTH_LONG).show();
                Name.setText(null);
                Password.setText(null);
                Intent intent = new Intent(Login.this, Select.class);
                intent.putExtra("ID",us);
                startActivity(intent);
                flag=true;
                break;
            }
            else
            {
               // Intent intent = new Intent(Login.this, Login.class);
               // startActivity(intent);

                Name.setText(null);
                Password.setText(null);


            }

        }
        if( flag==false) {

            counter--;
            if (counter == 0)
                Login.setEnabled(false);
            Info.setText("Num of attempts remaining :" + String.valueOf(counter));
        }

    }


    public static Boolean RegInsert()
    {
        UN=Registration.getUsername();
        PA=Registration.getPassword();

        Boolean b=myDb.insertData(UN,PA);

        return b;
    }

    public void viewAll()
    {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
        }

        // Show all data
        showMessage("Data", buffer.toString());
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public boolean Validatenull(String u,String p)
    {
        if(u.isEmpty()|| p.isEmpty())
            return false;
        else
            return true;
    }

   /* public static String grtUserID()
    {
        return UsNa;
    }*/

}



