package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btFace, btGoogle, btInsta, btAboutHotel, btAccomadtion, btFacilites, btServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFace = findViewById(R.id.btFace);
        btGoogle = findViewById(R.id.btGoogle);
        btInsta = findViewById(R.id.btMbail);
        btAboutHotel = findViewById(R.id.btAboutHotel);
        btFacilites = findViewById(R.id.btFacilities);
        btServices = findViewById(R.id.btServices);

        btFacilites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FacilitesActivity.class));
            }
        });

        btAboutHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListViewHotel.class));
            }
        });

        btServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServicesActivity.class));
            }
        });

        btAccomadtion = findViewById(R.id.btAccomadtion);
        btAccomadtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Accomdation.class);
                intent1.putExtra("docId", getIntent().getStringExtra("docID"));
                startActivity(intent1);
            }
        });

        btInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openInstagramPage();
                    }
                });
                thread.start();
            }
        });

        btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openGooglePage();
                    }
                });
                thread.start();
            }
        });

        btFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openFacebookPage();
                    }
                });
                thread.start();
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Intent intent = getIntent();
        String docId = intent.getStringExtra("docID");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle item clicks here
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.profile:
                        // Handle item 1 click
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        intent.putExtra("docID",docId);
                        startActivity(intent);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));

                        return true;
                    case R.id.settings:
                        logout();
                        return true;
                }
                return false;
            }
        });
    }
    private void logout() {
        startActivity(new Intent(MainActivity.this, LogIn.class));
        finish();
    }


    private void openFacebookPage() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<mohtadedrabi>")));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<mohtadedrabi>")));
        }
    }

    private void openGooglePage() {
        String googlePageUrl = "https://www.google.com/search?q=wikipedia&rlz=1C1SQJL_arPS976PS976&sxsrf=AB5stBgi3RszjgTcVWuOf71bnPVGSluTDg%3A1689034273339&ei=IZ6sZMivFNDMsAf41r2gCA&oq=wekaped&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQARgAMgcIABCKBRBDMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKOgoIABBHENYEELADOgoIABCKBRCwAxBDOhUILhCKBRDHARDRAxDIAxCwAxBDGAE6DwguEIoFEMgDELADEEMYAToFCAAQgAQ6EQguEIAEEMcBEK8BEJgFEJkFOggIABCABBDLAToUCC4QgAQQxwEQrwEQmAUQmQUQywE6BwgjEIoFECc6BAgjECc6DQgAEIoFELEDEIMBEEM6DgguEIAEEOcEEMcBENEDOggIABCABBDnBDoLCAAQgAQQsQMQgwE6BwgjEOoCECc6DQguEMcBENEDEOoCECc6BwguEOoCECc6DwgAEIoFEOoCELQCEEMYAjoVCC4QigUQxwEQ0QMQ6gIQtAIQQxgCOgsILhCABBCxAxCDAToHCC4QigUQQzoLCC4QgAQQxwEQ0QM6BQguEIAEOggIABCABBCxAzoLCC4QgwEQsQMQgAQ6EQguEIAEELEDEIMBEMcBENEDOhEILhCKBRCxAxCDARDHARDRAzoKCAAQigUQsQMQQzoTCC4QigUQsQMQgwEQxwEQ0QMQQzoNCC4QigUQxwEQ0QMQQzoHCC4QgAQQCjoWCC4QgAQQxwEQrwEQmAUQmQUQChDLAToKCAAQgAQQChDLAToQCC4QgAQQxwEQrwEQChDLAToVCC4QigUQQxCXBRDcBBDeBBDgBBgDSgQIQRgAULIEWNeJAmDXpAJoCnABeACAAb8BiAGaG5IBBDAuMjGYAQCgAQGwARTAAQHIAQ3aAQQIARgI2gEGCAIQARgB2gEGCAMQARgU&sclient=gws-wiz-serp";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googlePageUrl));
        startActivity(intent);
    }

    private void openInstagramPage() {
        String instagramPageUrl = "https://www.instagram.com/<mohtade__drabi_11>";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramPageUrl));
        intent.setPackage("com.instagram.android");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagramPageUrl)));
        }
    }
}