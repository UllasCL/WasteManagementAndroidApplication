package com.ullas.firstphase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class checkidactivity extends AppCompatActivity
{
    EditText idfield;
    Button get;
   Button showView;
    public static Complaints idact;
    public Integer I,id1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkidactivity);

        idfield=findViewById(R.id.etid);
        get=findViewById(R.id.btcheck);
        idact=Login.cpmanual1;
        showView=findViewById(R.id.btnView);

        get.setOnClickListener(new View.OnClickListener( )
        {
            @Override
            public void onClick(View v)
            {
                String idfields;
                idfields= ((EditText)findViewById(R.id.etid)).getText().toString();

                if(idfields.isEmpty())
                {
                    Toast.makeText(checkidactivity.this,"Enter complaint ID",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(checkidactivity.this,checkidactivity.class));
                }
                else
                    {
                    id1 = Integer.parseInt(idfields);

                    viewAll();
                    idfield.setText(null);
                    }
            }
        });

        showView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

              viewAll1();
            }
        });

    }
   public  void viewAll1()
    {

        String text = null;

        Intent intent = getIntent();
        String str = intent.getStringExtra("ID");
        text=str;
        boolean flag=false;
        //Toast.makeText(Select.this,text,Toast.LENGTH_LONG).show();


        Cursor res = idact.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext())
        {
            if(text.equals(res.getString(1)))
            {
                buffer.append("Complaint Id :" + res.getString(0) + "\n");
               // buffer.append("Id :" + res.getString(1) + "\n");
                buffer.append("ADDRESS :\n" + res.getString(2) + "\n\n");
                flag=true;
            }

        }
        if(flag==false)
        {
            showMessage("Message", "No record found");
        }

        // Show all data
        if(flag==true)
         showMessage("Complaints", buffer.toString());
    }

    public  void viewAll()
    {
        Cursor res = idact.getAllData();

        StringBuffer buffer = new StringBuffer();
        boolean flag=false;
        while(res.moveToNext())
        {
            String CurId=res.getString(0);
            I=Integer.parseInt(CurId);
            if(id1==I)
            {
                buffer.append( "Sorry \nStill work is under process\n");
                flag=true;
                break;
            }
        }
        if(flag==false)
             buffer.append( "Your complaints have been resolved\n");
        showMessage("Message", buffer.toString());
    }
    public  void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }




}
