package com.teenvan.frs;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignInActivity extends AppCompatActivity {
    // Declaration of member variables
    private Button mLoginButton, mVacanciesButton;
    private EditText mUsername , mPassword;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Typeface tv = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Regular.otf");

        //Referencing the UI elements
        mLoginButton = (Button)findViewById(R.id.loginButton);
        mVacanciesButton = (Button)findViewById(R.id.vacanciesButton);
        mUsername = (EditText)findViewById(R.id.username);
        mPassword = (EditText)findViewById(R.id.password);
        mRelativeLayout = (RelativeLayout)findViewById(R.id.relativeView);

        mLoginButton.setTypeface(tv);
        mVacanciesButton.setTypeface(tv);
        mUsername.setTypeface(tv);
        mPassword.setTypeface(tv);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the required strings
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    // Show error
                    Snackbar.make(mRelativeLayout,"Please enter all details",Snackbar.LENGTH_SHORT)
                            .show();
                }else{
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if( e==null){
                                // Successfully logged in
                                Intent intent = new Intent(SignInActivity.this,
                                        MainActivity.class);
                                startActivity(intent);
                            }else{
                                Log.d("SignInActivity","Error",e);
                                Toast.makeText(SignInActivity.this,"There was some error!" +
                                        "Try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        mVacanciesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the applicant activity
                Intent intent = new Intent(SignInActivity.this,ApplicationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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
}
