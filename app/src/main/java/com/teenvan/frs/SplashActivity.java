package com.teenvan.frs;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    // Declaration of member variables
    private Button mApplicantButton , mMemberButton;
    private TextView mTitle, mLnmiit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar bar = getSupportActionBar();
        if(bar != null){
            bar.hide();
        }
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-SemiBold.otf");
        Typeface tv = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Regular.otf");
        // Referencing the UI elements

        mApplicantButton = (Button)findViewById(R.id.applicantButton);
        mMemberButton = (Button)findViewById(R.id.memberButton);
        mTitle = (TextView)findViewById(R.id.frsText);
        mLnmiit = (TextView)findViewById(R.id.instituteText);

        mTitle.setTypeface(tv);
        mLnmiit.setTypeface(tv);
        mApplicantButton.setTypeface(tv);
        mMemberButton.setTypeface(tv);

        // Setting up the click listeners
        mApplicantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the vacancy activity
                Intent intent = new Intent(SplashActivity.this, ApplicationActivity.class);
                startActivity(intent);
            }
        });

        mMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the sign in activity
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
