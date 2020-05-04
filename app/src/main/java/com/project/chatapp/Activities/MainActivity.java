package com.project.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.project.chatapp.R;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener  {
    //


    private Button loginButton;
    private Button createAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        ParseInstallation.getCurrentInstallation().saveInBackground();

        createAccountButton = (Button) findViewById(R.id.createButtonId);
        loginButton = (Button) findViewById(R.id.loginButton);


        createAccountButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);




       ParseUser user = new ParseUser();


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //we are good!
                    Toast.makeText(getApplicationContext(), "Yeahh! All is good!", Toast.LENGTH_LONG).show();
                }else {
                    //something went wrong
                }

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.loginButton:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

                break;
            case R.id.createButtonId:
                startActivity(new Intent(MainActivity.this, CreateAccount.class));




        }

}
}
