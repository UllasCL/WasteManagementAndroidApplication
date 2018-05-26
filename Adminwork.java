package com.ullas.firstphase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.ullas.firstphase.Complaints.TABLE_NAME;

public class adminwork extends AppCompatActivity
{

    Button view,Resolve,Home;
    EditText Res;
    public static Complaints com;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminwork);
        com=Login.cpmanual1;


        view=(Button)findViewById(R.id.buttonviewall);
        Resolve=(Button)findViewById(R.id.ResBt);
        Home=(Button)findViewById(R.id.HomeBt);


        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                viewAll();
            }
        });
        Resolve.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               String Id= ((EditText) findViewById(R.id.ResEt)).getText().toString();

               if(Id.isEmpty())
               {
                   Toast.makeText(adminwork.this,"Enter ID",Toast.LENGTH_LONG).show();
                   startActivity(new Intent(adminwork.this,adminwork.class));
               }
               else
                   {
                   Integer coMID = Integer.parseInt(Id);
                   Integer deletedRows = com.deleteData(coMID);
                   if (deletedRows > 0)
                       Toast.makeText(adminwork.this, "Resolved", Toast.LENGTH_LONG).show();
                   else
                       Toast.makeText(adminwork.this, "Enter ID", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(adminwork.this, adminwork.class));
               }
            }
        });

        Home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(adminwork.this,Login.class));
            }
        });


    }

    public  void viewAll()
    {
        Cursor res = com.getAllData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Complaint Id :" + res.getString(0) + "\n");
            buffer.append("Id :" + res.getString(1) + "\n");
            buffer.append("ADDRESS :\n" + res.getString(2) + "\n\n");

        }

        // Show all data
        showMessage("Data", buffer.toString());
    }
    public  void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

  /* public static boolean LoadInsert()
    {
        // id=LoadActivity.getText();
        // loc=LoadActivity.getAddress();

        boolean b= com.insertData(id,loc);
        return b;
    }*/
   /* public static Boolean SubInsert()
    {
        U=LoadActivity.getUserId();
        P=LoadActivity.getLoc();


        Boolean b=com.insertData(U,P);

        return b;
    }*/

}
