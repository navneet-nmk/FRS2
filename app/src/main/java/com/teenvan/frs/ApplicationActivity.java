package com.teenvan.frs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

public class ApplicationActivity extends AppCompatActivity {
    // Declaration of member variables
    private EditText mName,mEmail,mContact,mInstitute,mAge;
    private Button mConfirmButton, mUploadButton;
    private static final int FILE_SELECT_CODE = 0;
    private byte[] parseFileData;
    private ParseFile file;

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
                // Upload the cv and save to parse
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try{
                    startActivityForResult(Intent.createChooser(intent,"Select your CV"),
                            FILE_SELECT_CODE);
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(ApplicationActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the strings from the edit texts
                final String name = mName.getText().toString();
                final String email = mEmail.getText().toString();
                final String contact = mContact.getText().toString();
                final String institute = mInstitute.getText().toString();
                final String age = mAge.getText().toString();
                if (name.isEmpty() || email.isEmpty() || contact.isEmpty()
                        || institute.isEmpty() || age.isEmpty()) {
                    Toast.makeText(ApplicationActivity.this, "Enter all details",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Create a Parse Object
                    if(parseFileData!=null) {
                      file = new ParseFile(parseFileData);
                    }
                    file.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if( e==null){
                                ParseUser user = new ParseUser();
                                user.put("UserType","Applicant");
                                user.setUsername(name);
                                user.setEmail(email);
                                user.put("Contact", contact);
                                user.put("Institute",institute);
                                user.put("Age",age);
                                user.put("CV",file);
                                user.signUpInBackground(new SignUpCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e==null){
                                            // Success
                                            Intent intent = new Intent(ApplicationActivity.this,
                                                    ExperienceActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Log.e("SignUpUser","Error",e);
                                            Toast.makeText(ApplicationActivity.this,
                                                    "Error signing up",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else{

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d("File", "File Uri: " + uri.toString());
                    Log.d("File","File URi path "+uri.getPath());
                    try {
                        InputStream stream = getContentResolver().openInputStream(uri);
                        byte[] inputData = getBytes(stream);
                        parseFileData = inputData;


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    // Get the path

                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
