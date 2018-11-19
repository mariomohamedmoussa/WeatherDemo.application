package com.example.mouss.weatherappdemo.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mouss.weatherappdemo.R;
import com.example.mouss.weatherappdemo.utils.Collection;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView tv;
    private Button btnVena;
    private Button btnCairo;
    private Button btnRoma;
    private Button btnparies;
    private Button btnMadrid;
    private Button btnMyloc;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponentGui();
        event();
    }

    private void event() {
        btnMyloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "myLoc";
                startActivity(i);
            }
        });
        btnMadrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "Loc";
                Collection.lat = 40.416775;
                Collection.lon = -3.703790;
                startActivity(i);
            }
        });
        btnparies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "Loc";
                Collection.lat = 48.8480606;
                Collection.lon = 2.3548647;
                startActivity(i);
            }
        });
        btnMyloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "Loc";
                startActivity(i);
            }
        });
        btnRoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "Loc";
                Collection.lat = 41.9102416;
                Collection.lon = 12.8161956;
                startActivity(i);
            }
        });
        btnVena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "Loc";
                Collection.lat = 48.2208286;
                Collection.lon = 16.5201461;
                startActivity(i);
            }
        });
        btnCairo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Collection.Loc = "Loc";
                Collection.lat = 30.0596185;
                Collection.lon = 31.3285055;
                startActivity(i);
            }
        });
    }

    private void initComponentGui() {
        tv = (TextView) findViewById(R.id.tv);
        btnVena = (Button) findViewById(R.id.btnVena);
        btnCairo = (Button) findViewById(R.id.btnCairo);
        btnRoma = (Button) findViewById(R.id.btnRoma);
        btnparies = (Button) findViewById(R.id.btnparies);
        btnMadrid = (Button) findViewById(R.id.btnMadrid);
        btnMyloc = (Button) findViewById(R.id.btnMyloc);
    }


    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);
    }

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Collection.currentLocation = locationResult.getLastLocation();

            }
        };

    }


}
