package com.teenvan.frs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class ApplicationActivity extends AppCompatActivity {
    // Declaration of member variables
    private EditText mName,mEmail,mContact,mInstitute,mAge;
    private Button mConfirmButton, mUploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        // Referencing the UI elements
        mUploadButton = (Button)findViewById(R.id.uploadButton);
        mName = (EditText)findViewById(R.id.nameEditText);
        mEmail = (EditText)findViewById(R.id.emailEditText);
        mContact = (EditText)findViewById(R.id.contactEditText);
        mInstitute = (EditText)findViewById(R.id.instituteEditText);
        mAge = (EditText)findViewById(R.id.ageEditText);
        mConfirmButton = (Button)findViewById(R.id.confirmButton);

        // Set the click listener of the button
        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Upload the cv
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the strings from the edit texts
                final String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String contact = mContact.getText().toString();
                String institute = mInstitute.getText().toString();
                String age = mAge.getText().toString();
                 if(name.isEmpty() || email.isEmpty() || contact.isEmpty()
                         || institute.isEmpty() || age.isEmpty()){
                     Toast.makeText(ApplicationActivity.this,"Enter all details",
                             Toast.LENGTH_SHORT).show();
                 }else{
                     // Create a Parse Object
                     ParseObject application = new ParseObject("Application");
                     application.put("Name",name);
                     application.put("Email",email);
                     application.put("Contact",contact);
                     application.put("Institute",institute);
                     application.put("Age",age);
                     application.saveEventually(new SaveCallback() {
                         @Override
                         public void done(ParseException e) {
                             if(e==null){
                                 // Success
                                 Toast.makeText(ApplicationActivity.this,"Successfully saved",
                                         Toast.LENGTH_SHORT).show();
                                 // Open experience activity
                                 Intent intent = new Intent(ApplicationActivity.this,
                                         ExperienceActivity.class);
                                 intent.putExtra("Name",name);
                                 startActivity(intent);
                             }else{
                                 // Error
                                 Log.e("Application","Error",e);
                                 Toast.makeText(ApplicationActivity.this,
                                         "There was some problem saving. Please try again",
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
