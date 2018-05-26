package com.ullas.firstphase;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


public class Select extends AppCompatActivity
{
        private  static  final String TAG="Select";

        private static final int ERROR_DIALOG_REQUEST =9001;
        public static Complaints checkd;

        Button home;

    Button manual,check;
        @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        manual=(Button)findViewById(R.id.btnManual);
        check=(Button)findViewById(R.id.btnStatus);//today
        home=(Button)findViewById(R.id.btnBlynk);


        checkd= Login.cpmanual1;

        if(isServicesOK())
            init();

        manual.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String text = null;

                Intent intent = getIntent();
                String str = intent.getStringExtra("ID");
                text=str;
                //Toast.makeText(Select.this,text,Toast.LENGTH_LONG).show();

                Intent intent1=new Intent(Select.this,maualgo.class);
                intent1.putExtra("ID",text);
                startActivity(intent1);
            }
        });



        check.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                //startActivity(new Intent(Select.this,checkidactivity.class));
               // startActivity(new Intent(Select.this,checkidactivity.class));


                String text = null;

                Intent intent = getIntent();
                String str = intent.getStringExtra("ID");
                text=str;
                //Toast.makeText(Select.this,text,Toast.LENGTH_LONG).show();

                Intent intent2=new Intent(Select.this,checkidactivity.class);
               intent2.putExtra("ID",text);
                startActivity(intent2);

                /*String text = null;

                Intent intent = getIntent();
                String  str = intent.getStringExtra("ID");
                text=str;

               // Toast.makeText(Select.this, text, Toast.LENGTH_LONG).show();


                Cursor res = checkd.getAllData();
                Boolean flag=false;
                String uid;
               while (res.moveToNext())
                {
                    uid=res.getString(0);

                  if(uid.equals(text))
                    {
                        Toast.makeText(Select.this, "                  Sorry \n Still work is under process", Toast.LENGTH_LONG).show();
                        flag=true;
                        break;

                    }
                }

                if(flag==false)
                    Toast.makeText(Select.this,"Resolved your complaints",Toast.LENGTH_LONG).show();
*/
            }

        });

        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openBlynk();
                Intent openBlynk1=getPackageManager().getLaunchIntentForPackage("cc.blynk");
                startActivity(openBlynk1);
               // Intent Blunk=(Select.this);
                //startActivity(Blunk);
            }
        });

    }

    private void init()
    {
        Button buttonMap=(Button)findViewById(R.id.btnMap);

        buttonMap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String text = null;

                Intent intent = getIntent();
                String str = intent.getStringExtra("ID");
                text=str;
               // Toast.makeText(Select.this,text,Toast.LENGTH_LONG).show();

                Intent intent1=new Intent(Select.this,MapActivity.class);
                intent1.putExtra("ID",text);
                startActivity(intent1);
            }
        });
    }


    public  boolean isServicesOK()
    {
        Log.d(TAG, "isServicesOK:checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Select.this);

        if(available== ConnectionResult.SUCCESS)
        {
            Log.d(TAG,"is Services ok : google play services is working ");
            return  true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            Log.d(TAG,"is Services error : google play services is not working ");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Select.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else
        {
            Toast.makeText(this,"you cannot make map request",Toast.LENGTH_LONG).show();
        }
        return false;
    }


    public void openBlynk(Context context)
    {
       try
        {
            Intent openBlynk1=getPackageManager().getLaunchIntentForPackage("cc.blynk");
            startActivity(openBlynk1);
           // context.getPackageManager()
             //       .getLaunchIntentForPackage("cc.blynk");
           // startActivity(openBlynk());
            //return new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        }
        catch(Exception e)
        {
            Toast.makeText(this,"Sorry for blynk",Toast.LENGTH_LONG).show();
            // new Intent(Intent.ACTION_VIEW,Uri.parse(""));
        }
    }


}
