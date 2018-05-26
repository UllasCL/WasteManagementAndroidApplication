package com.ullas.firstphase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private  static  final String TAG="MapActivity";

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private static final int code = 1234;

    private Boolean mp = false;

    private GoogleMap mMap;
    private FusedLocationProviderClient mfusedlocationproviderclient;
    private static final float DEFAULT_ZOOM = 15f;

    private Button btnAddress;
    Geocoder geocoder;
    List<Address> addresses;

    public Double latitude,longitude;

    public  static String fullAddress,address,subLocality,area,city,country,postalcode;




    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onMapReady : Map is ready");
        mMap = googleMap;
        if (mp)
        {
            getDevicelocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            mMap.setMyLocationEnabled(true);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getLocationPermission();




        btnAddress = (Button)findViewById(R.id.btnAdd);

        geocoder = new Geocoder(this, Locale.getDefault());

        btnAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                try
                {
                    addresses = geocoder.getFromLocation(latitude,longitude,1);

                    address =addresses.get(0).getAddressLine(0);
                    area = addresses.get(0).getLocality();
                    city = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    subLocality  = addresses.get(0).getSubLocality();
                    postalcode =addresses.get(0).getPostalCode();

                    //fullAddress=address+","+area+"\n "+city+"\n"+country+"\n"+subLocality+"";
                    fullAddress=address;

                    //textView.setText(fullAddress);
                    // Toast.makeText(MapActivity.this,""+ fullAddress+"", Toast.LENGTH_SHORT).show();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                // Toast.makeText(MapActivity.this,""+ latitude+"", Toast.LENGTH_SHORT).show();
                // Toast.makeText(MapActivity.this,""+ longitude+"", Toast.LENGTH_SHORT).show();
                //Toast.makeText(MapActivity.this,""+ address+"", Toast.LENGTH_SHORT).show();


                String text = null;

                Intent intent = getIntent();
                String str = intent.getStringExtra("ID");
                text=str;
               // Toast.makeText(MapActivity.this,text, Toast.LENGTH_SHORT).show();


                Intent intent1=new Intent(MapActivity.this,LoadActivity.class);
                intent1.putExtra("ID",text);
                startActivity(intent1);


            }
        });
    }

    public static String getAddress()
    {
        return fullAddress;
    }
    public static String getArea()
    {
        return area;
    }
    public static String getCity()
    {
        return city;
    }
    public static String getCountry()
    {
        return country;
    }
    public static String getSubLocality() {return subLocality;}



    public void getDevicelocation()
    {
        Log.d(TAG,"getdevicelocation:getting the current location");
        mfusedlocationproviderclient= LocationServices.getFusedLocationProviderClient(this);
        try
        {
            if(mp)
            {
                final Task location=mfusedlocationproviderclient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener()
                {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG,"oncomplte : found location");
                            Location currentlocation =(Location) task.getResult();
                            latitude=currentlocation.getLatitude();
                            longitude=currentlocation.getLongitude();

                            movecamera(new LatLng(currentlocation.getLatitude(),currentlocation.getLongitude()),DEFAULT_ZOOM);
                        }
                        else
                        {
                            Log.d(TAG,"oncomplte : not found location");
                            Toast.makeText(MapActivity.this,"unable to get current location",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        }
        catch (SecurityException e)
        {
            Log.d(TAG,"get device location:Security exception"+e.getMessage());
        }

    }

    private  void movecamera(LatLng latlang,float zoom)
    {
        Log.d(TAG,"move camera: moving camera to "+latlang.latitude+","+latlang.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlang,zoom));
    }


    private void initMap()
    {
        Log.d(TAG,"initMap : Initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync( MapActivity.this);
    }

    private void getLocationPermission()
    {
        Log.d(TAG,"getLocationPermission : getting location permission");

        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                mp=true;
                initMap();
            }
            else
            {
                ActivityCompat.requestPermissions(this,permissions,code);
            }
        }
        else
        {
            ActivityCompat.requestPermissions(this,permissions,code);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        Log.d(TAG,"onRequestPermissionsResult : Called");

        mp=false;
        switch(requestCode)
        {
            case code:{
                if(grantResults.length>0 )
                {
                    for(int i=0;i<grantResults.length;i++)
                    {
                        if(grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                        {
                            mp=false;
                            Log.d(TAG,"onRequestPermissionsResult : Permission failed");
                            return;
                        }
                    }
                    Log.d(TAG,"onRequestPermissionsResult : Permission Granted");

                    mp=true;

                    initMap();
                }
            }
        }
    }



}
