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

public class maualgo extends AppCompatActivity {


    private EditText Location,Area,City,Country;
    String location;
     String area;
     String city;
     String country,text;
    private Button submit,Home;
    String FullAddress;
    public  static Complaints cpmanual;

    public static long COMID;
    public Integer I;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maualgo);
        Location=(EditText)findViewById(R.id.etLoction);
        Area=(EditText)findViewById(R.id.etArea);
        City=(EditText)findViewById(R.id.etCity);
        Country=(EditText)findViewById(R.id.etCountry);

        submit=(Button)findViewById(R.id.btnsubmit);
        Home=(Button)findViewById(R.id.HomeBt);
        cpmanual=Login.cpmanual1;




        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                location=((EditText)findViewById(R.id.etLoction)).getText().toString();
                area=((EditText)findViewById(R.id.etArea)).getText().toString();
                city=((EditText)findViewById(R.id.etCity)).getText().toString();
                country=((EditText)findViewById(R.id.etCountry)).getText().toString();

                FullAddress=location+" "+area+" "+city+" "+country;

                text = null;
                Intent intent = getIntent();
                String str = intent.getStringExtra("ID");
                text=str;
                //Toast.makeText(LoadActivity.this,text,Toast.LENGTH_LONG).show();
                //Toast.makeText(maualgo.this,FullAddress,Toast.LENGTH_LONG).show();

                // Toast.makeText(LoadActivity.this,text,Toast.LENGTH_LONG).show();

                Boolean b=maualgo.LoadInsertmanual(text,FullAddress);
                 viewAll();

                if(b)
                {

                    Toast.makeText(maualgo.this,"successfully uploaded",Toast.LENGTH_LONG).show();
                    Location.setText(null);
                    Area.setText(null);
                    City.setText(null);
                    Country.setText(null);

                }
                else
                {
                    Toast.makeText(maualgo.this,"not uploaded",Toast.LENGTH_LONG).show();
                }


            }


        });



        Home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(maualgo.this,Login.class));
            }
        });

    }


    public static boolean LoadInsertmanual(String id,String loc)
    {
        boolean b= cpmanual.insertData(id,loc);
        return b;
    }



    public  void viewAll()
    {
        Cursor res = cpmanual.getAllData();
        COMID=Complaints.comID;
        // Toast.makeText(LoadActivity.this," "+COMID+" ",Toast.LENGTH_LONG).show();

       /* while(res.moveToNext())
        {
            String CurId=res.getString(0);
            I=Integer.parseInt(CurId);
        }*/
       /* if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }*/
        I=(int)(long)COMID;
        // I=I+1;
        String S=I.toString();
        StringBuffer buffer = new StringBuffer();
        buffer.append("Please note your complaint ID :" + S + "\n");

       /* while (res.moveToNext())
        {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
        }*/

        // Show all data
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
