package com.project.chatapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.project.chatapp.R;
import com.project.chatapp.util.ProgressGenerator;


public class CreateAccount extends AppCompatActivity implements ProgressGenerator.onCompleteListener, View.OnClickListener{


    private EditText emailAddress;
    private EditText password;
    private EditText username;
    private ProgressGenerator progressGenerator;
    private ActionProcessButton createAccountButton;
    private TextView hasAccount;
    private ImageView logo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);



        emailAddress = (EditText) findViewById(R.id.userEmailId);
        password = (EditText) findViewById(R.id.usernamePasswordId);
        username = (EditText) findViewById(R.id.usernameAccountId);
        hasAccount = (TextView)findViewById(R.id.hasAccountTV);
        logo = (ImageView) findViewById(R.id.logoImageView);

        hasAccount.setOnClickListener(this);
        logo.setOnClickListener(this);

        progressGenerator = new ProgressGenerator(this);

        createAccountButton = (ActionProcessButton) findViewById(R.id.userNameCreateAccountId);

        createAccountButton.setMode(ActionProcessButton.Mode.PROGRESS);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });



    }


    private void createAccount(){
       final String uEmail = emailAddress.getText().toString();
        final String uName = username.getText().toString();
        final String pWord = password.getText().toString();


        if ( uEmail.equals("") || uName.equals("") || pWord.equals(""))

        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(CreateAccount.this);
            dialog.setTitle("Empty Fields");
            dialog.setMessage("Please complete the form");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else {

            ParseUser user = new ParseUser();

            user.setUsername(uName);
            user.setPassword(pWord);
            user.setEmail(uEmail);



            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {
                        progressGenerator.start(createAccountButton);
                        createAccountButton.setEnabled(false);

                        emailAddress.setEnabled(false);
                        username.setEnabled(false);
                        password.setEnabled(false);

                        //logs user in automatically after account creation
                        logUserIn(uName, pWord);
                    }

                }
            });

        }

    }

    private void logUserIn(String uName, String pWord) {
        if (!uName.equals("") || !pWord.equals("")) {

            ParseUser.logInInBackground(uName, pWord, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (e == null) {
                        Log.v("USER LOGGED IN IS:", parseUser.getUsername() );

                    }else {

                    }
                }
            });
        }else {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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
    public void onComplete() {

        startActivity(new Intent(CreateAccount.this, ChatActivity.class));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hasAccountTV:
                startActivity(new Intent(CreateAccount.this, LoginActivity.class));

                break;
            case R.id.logoImageView:
                startActivity(new Intent(CreateAccount.this,MainActivity.class));




        }

    }


}


