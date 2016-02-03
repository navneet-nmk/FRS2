package com.teenvan.frs;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MemberViewActivity extends AppCompatActivity {

    // Declaration of member variables
    private TextView mName , mDesig , mBTech , mMtech , mPhd , mResearch , mExp;
    private TextView mRate , mResume , mProfile ;
    private Button mConfirm , mViewResume ;
    private ArrayList<Applicant> mApplicants;
    private String applicantId = "";
    private String applicantName = "";
    private String applicantDesig = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Typeface initialization

        Typeface tv = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Regular.otf");

        // Referencing the UI Elements
        mName = (TextView)findViewById(R.id.candidateNameText);
        mDesig = (TextView)findViewById(R.id.desigText);
        mBTech = (TextView)findViewById(R.id.btechCText);
        mMtech = (TextView)findViewById(R.id.mtechCText);
        mPhd = (TextView)findViewById(R.id.phdCText);
        mResearch = (TextView)findViewById(R.id.researchCText);
        mExp = (TextView)findViewById(R.id.expereinceCText);

        mRate = (TextView)findViewById(R.id.rateText);
        mResume = (TextView)findViewById(R.id.resumeText);
        mProfile = (TextView)findViewById(R.id.profileText);

        mConfirm = (Button)findViewById(R.id.confirmCButton);
        mViewResume = (Button)findViewById(R.id.viewResumeCButton);


        // Set the typefaces
        mName.setTypeface(tv);
        mDesig.setTypeface(tv);
        mBTech.setTypeface(tv);
        mMtech.setTypeface(tv);
        mPhd.setTypeface(tv);
        mResearch.setTypeface(tv);
        mExp.setTypeface(tv);
        mRate.setTypeface(tv);
        mResume.setTypeface(tv);
        mProfile.setTypeface(tv);
        mConfirm.setTypeface(tv);
        mViewResume.setTypeface(tv);


        // Data Collection
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("IsShortlisted",false);
        query.whereEqualTo("UserType","Applicant");
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                applicantName = parseUser.getUsername();
                applicantDesig = parseUser.getString("Designation");
                applicantId = parseUser.getObjectId();
                mName.setText(parseUser.getUsername());
                mDesig.setText(parseUser.getString("Designation"));
                mBTech.setText(parseUser.getString("BtechDivision")+" "+parseUser.getString("BtechInstitute"));
                mMtech.setText(parseUser.getString("MtechDivision")+" "+parseUser.getString("MtechInstitute"));
                mPhd.setText(parseUser.getString("PhDInstitute"));
                mResearch.setText("No of research papers : "+parseUser.getString("ResearchPapers"));
                mExp.setText("No of years of experience : "+parseUser.getString("Experience"));
            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make the candidate a shortlisted candidate
                ParseObject cand = new ParseObject("Shortlisted");
                cand.put("ApplicantId",applicantId);
                cand.put("Rating",3.5);
                cand.put("Name",applicantName);
                cand.put("Designation",applicantDesig);
                cand.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if( e == null){
                            // Show a dialog
                            ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
                            userQuery.whereEqualTo("UserType","Applicant");
                            userQuery.whereEqualTo("Username",applicantName);
                            userQuery.whereEqualTo("Designation",applicantDesig);
                            userQuery.getFirstInBackground(new GetCallback<ParseUser>() {
                                @Override
                                public void done(ParseUser parseUser, ParseException e) {
                                    if(e == null){
                                        if(parseUser != null){
                                            parseUser.put("IsShortlisted",true);
                                            parseUser.saveInBackground(new SaveCallback() {
                                                @Override
                                                public void done(ParseException e) {
                                                    // Data Collection
                                                    if (e == null) {
                                                        ParseQuery<ParseUser> query = ParseUser.getQuery();
                                                        query.whereEqualTo("IsShortlisted", false);
                                                        query.whereEqualTo("UserType", "Applicant");
                                                        query.getFirstInBackground(new GetCallback<ParseUser>() {
                                                            @Override
                                                            public void done(ParseUser parseUser, ParseException e) {
                                                                applicantName = parseUser.getUsername();
                                                                applicantDesig = parseUser.getString("Designation");
                                                                applicantId = parseUser.getObjectId();
                                                                mName.setText(parseUser.getUsername());
                                                                mDesig.setText(parseUser.getString("Designation"));
                                                                mBTech.setText(parseUser.getString("BtechDivision") + " " + parseUser.getString("BtechInstitute"));
                                                                mMtech.setText(parseUser.getString("MtechDivision") + " " + parseUser.getString("MtechInstitute"));
                                                                mPhd.setText(parseUser.getString("PhDInstitute"));
                                                                mResearch.setText("No of research papers : " + parseUser.getString("ResearchPapers"));
                                                                mExp.setText("No of years of experience : " + parseUser.getString("Experience"));
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        }else{
                                            Toast.makeText(MemberViewActivity.this,
                                                    "That's it for today!",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MemberViewActivity.this,SplashActivity.class);
                                            startActivity(intent);
                                        }
                                    }else{
                                        Toast.makeText(MemberViewActivity.this,
                                                "That's it for today!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MemberViewActivity.this,SplashActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });



                        }else{
                            Toast.makeText(MemberViewActivity.this,
                                    "There was a problem saving your ratings. " +
                                            "Please try again !",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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
}
