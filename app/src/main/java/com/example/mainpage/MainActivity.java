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
    private Button btFace , btGoogle ,btInsta , btAboutHotel, btFacilites,btServices;
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
        btFacilites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FacilitesActivity.class));

            }
        });
        btServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServicesActivity.class));

            }
        });


        btInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the Instagram page here
                openInstagramPage();
            }
        });


        btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the Google page here
                openGooglePage();
            }
        });


        btFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle opening the Facebook page here
                openFacebookPage();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item clicks here
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.profile:
                        // Handle item 1 click
                        startActivity(new Intent(MainActivity.this, SignUp.class));
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
            // Open the Facebook page in the Facebook app
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<mohtadedrabi>")));
        } catch (ActivityNotFoundException e) {
            // Facebook app is not installed, open the Facebook page in a web browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<mohtadedrabi>")));
        }
    }
    private void openGooglePage() {
        String googlePageUrl = "https://www.google.com/search?q=wikipedia&rlz=1C1SQJL_arPS976PS976&sxsrf=AB5stBgi3RszjgTcVWuOf71bnPVGSluTDg%3A1689034273339&ei=IZ6sZMivFNDMsAf41r2gCA&oq=wekaped&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQARgAMgcIABCKBRBDMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKMgcIABCABBAKOgoIABBHENYEELADOgoIABCKBRCwAxBDOhUILhCKBRDHARDRAxDIAxCwAxBDGAE6DwguEIoFEMgDELADEEMYAToFCAAQgAQ6EQguEIAEEMcBEK8BEJgFEJkFOggIABCABBDLAToUCC4QgAQQxwEQrwEQmAUQmQUQywE6BwgjEIoFECc6BAgjECc6DQgAEIoFELEDEIMBEEM6DgguEIAEEOcEEMcBENEDOggIABCABBDnBDoLCAAQgAQQsQMQgwE6BwgjEOoCECc6DQguEMcBENEDEOoCECc6BwguEOoCECc6DwgAEIoFEOoCELQCEEMYAjoVCC4QigUQxwEQ0QMQ6gIQtAIQQxgCOgsILhCABBCxAxCDAToHCC4QigUQQzoLCC4QgAQQxwEQ0QM6BQguEIAEOggIABCABBCxAzoLCC4QgwEQsQMQgAQ6EQguEIAEELEDEIMBEMcBENEDOhEILhCKBRCxAxCDARDHARDRAzoKCAAQigUQsQMQQzoTCC4QigUQsQMQgwEQxwEQ0QMQQzoNCC4QigUQxwEQ0QMQQzoHCC4QgAQQCjoWCC4QgAQQxwEQrwEQmAUQmQUQChDLAToKCAAQgAQQChDLAToQCC4QgAQQxwEQrwEQChDLAToVCC4QigUQQxCXBRDcBBDeBBDgBBgDSgQIQRgAULIEWNeJAmDXpAJoCnABeACAAb8BiAGaG5IBBDAuMjGYAQCgAQGwARTAAQHIAQ3aAQQIARgI2gEGCAIQARgB2gEGCAMQARgU&sclient=gws-wiz-serp"; // Replace with the actual Google page URL or search result URL

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googlePageUrl));
        startActivity(intent);
    }
    private void openInstagramPage() {
        String instagramPageUrl = "https://www.instagram.com/<mohtade__drabi_11>"; // Replace <USERNAME> with the actual Instagram username or profile URL

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramPageUrl));
        intent.setPackage("com.instagram.android"); // Specify the package name of the Instagram app
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instagram app is not installed, open the Instagram page in a web browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(instagramPageUrl)));
        }
    }
}