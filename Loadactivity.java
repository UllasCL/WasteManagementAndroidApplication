package com.ullas.firstphase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.ullas.firstphase.adminwork.com;


public class LoadActivity extends AppCompatActivity
{
    private Button submit;

    public  String FullAddress;
    public String location;
    public String area;
    public String city;
    public String country;
    private EditText Location,Area,City,Country;
    public  static Complaints cp;
    public  String text;
    public Integer I;
    Button Home;

    public static long COMID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        cp=Login.cpmanual1;

        submit=(Button)findViewById(R.id.btnsubmit);
        Home=(Button)findViewById(R.id.HomeBt);

        Location=(EditText)findViewById(R.id.etLoction);
        Area=(EditText)findViewById(R.id.etArea);
        City=(EditText)findViewById(R.id.etCity);
        Country=(EditText)findViewById(R.id.etCountry);


        Location.setText(MapActivity.getSubLocality());
        Area.setText(MapActivity.getArea());
        City.setText(MapActivity.getCity());
        Country.setText(MapActivity.getCountry());




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
                //Toast.makeText(LoadActivity.this,FullAddress,Toast.LENGTH_LONG).show();

               // Toast.makeText(LoadActivity.this,text,Toast.LENGTH_LONG).show();
                Boolean b=LoadActivity.LoadInsert(text,FullAddress);
                viewAll();
                if(b)
              {

                  Toast.makeText(LoadActivity.this,"Your complaint successfully registered",Toast.LENGTH_LONG).show();
                  Location.setText(null);
                  Area.setText(null);
                  City.setText(null);
                  Country.setText(null);

              }
              else
              {
                  Toast.makeText(LoadActivity.this,"not registered",Toast.LENGTH_LONG).show();
              }


            }


        });

        Home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LoadActivity.this,Login.class));
            }
        });
    }
    public static boolean LoadInsert(String id,String loc)
    {
       boolean b= cp.insertData(id,loc);
        return b;
    }



    public  void viewAll()
    {
        Cursor res = cp.getAllData();
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


