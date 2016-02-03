package com.teenvan.frs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class ApplicationActivity extends AppCompatActivity  {
    // Declaration of member variables
    private EditText mName,mEmail,mContact,mInstitute,mAge, mPassword;
    private Button mConfirmButton;
    private ParseFile file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_application);

        Typeface tv = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Regular.otf");
        // Referencing the UI elements
        // mUploadButton = (Button)findViewById(R.id.uploadButton);
        mName = (EditText)findViewById(R.id.nameEditText);
        mPassword = (EditText)findViewById(R.id.passEditText);
        mEmail = (EditText)findViewById(R.id.emailEditText);
        mContact = (EditText)findViewById(R.id.contactEditText);
        mInstitute = (EditText)findViewById(R.id.instituteEditText);
        mAge = (EditText)findViewById(R.id.ageEditText);
        mConfirmButton = (Button)findViewById(R.id.confirmButton);

       // mUploadButton.setTypeface(tv);
        mName.setTypeface(tv);
        mPassword.setTypeface(tv);
        mEmail.setTypeface(tv);
        mConfirmButton.setTypeface(tv);
        mContact.setTypeface(tv);
        mInstitute.setTypeface(tv);
        mAge.setTypeface(tv);

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setProgressBarIndeterminateVisibility(true);
                // Get the strings from the edit texts
                final String password = mPassword.getText().toString();
                final String name = mName.getText().toString();
                final String email = mEmail.getText().toString();
                final String contact = mContact.getText().toString();
                final String institute = mInstitute.getText().toString();
                final String age = mAge.getText().toString();
                if (name.isEmpty() || password.isEmpty() || email.isEmpty() || contact.isEmpty()
                        || institute.isEmpty() || age.isEmpty()) {
                    setProgressBarIndeterminateVisibility(false);
                    Toast.makeText(ApplicationActivity.this, "Enter all details",
                            Toast.LENGTH_SHORT).show();
                } else {
                                ParseUser user = new ParseUser();
                                user.put("UserType", "Applicant");
                                user.setUsername(name);
                                user.setEmail(email);
                                user.setPassword(password);
                                user.put("Designation","CSE");
                                user.put("Contact", contact);
                                user.put("IsShortlisted",false);
                                user.put("Institute", institute);
                                user.put("Age", age);
                                user.signUpInBackground(new SignUpCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        setProgressBarIndeterminateVisibility(false);
                                        if (e == null) {
                                            // Success
                                            Intent intent = new Intent(ApplicationActivity.this,
                                                    EducationalActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Log.e("SignUpUser", "Error", e);
                                            Toast.makeText(ApplicationActivity.this,
                                                    "Error signing up",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_application, menu);
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
