package com.project.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.project.chatapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button signInButton;
    private EditText userName;
    private EditText password;
    private ImageView logo;
    private TextView createAct;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signInButton = (Button) findViewById(R.id.signInId);
        userName = (EditText) findViewById(R.id.usernameId);
        password = (EditText) findViewById(R.id.passwordId);
        logo = (ImageView) findViewById(R.id.logoLoginIV);
        createAct = (TextView) findViewById(R.id.createActlgTV);

        logo.setOnClickListener(this);
        createAct.setOnClickListener(this);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = userName.getText().toString();
                String pWord = password.getText().toString();

                if (!uName.equals("") || !pWord.equals("")) {
                    ParseUser.logInInBackground(uName, pWord, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (e == null) {

                                Toast.makeText(getApplicationContext(), "Login Successfully!"
                                        , Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(LoginActivity.this, ChatActivity.class));

                            }else {

                                Toast.makeText(getApplicationContext(), "Not logged in",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter username and Password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
            case R.id.createActlgTV:
                startActivity(new Intent(LoginActivity.this, CreateAccount.class));

                break;
            case R.id.logoLoginIV:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));




        }

    }
}
