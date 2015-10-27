package com.teenvan.frs;

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
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declaration of member variables
    private TextView mName , mDesig , mBTech , mMtech , mPhd , mResearch , mExp;
    private TextView mRate , mResume , mProfile ;
    private Button mConfirm , mViewResume ;
    private ArrayList<Applicant> mApplicants;
    private int position = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Data Collection
        ParseQuery<ParseObject> query = ParseQuery.getQuery("");
        query.whereEqualTo("", "");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    mApplicants = new ArrayList<Applicant>();
                    for (ParseObject applicant : list) {
                        Applicant candidate = new Applicant();
                        candidate.setApplicantName(applicant.getString("Name"));
                        candidate.setApplicantDesignation(applicant.getString("Designation"));
                        candidate.setApplicantBtech(applicant.getString("Btech"));
                        candidate.setApplicantMtech(applicant.getString("Mtech"));
                        candidate.setApplicantPhd(applicant.getString("Phd"));
                        candidate.setApplicantResearch(applicant.getInt("Research"));
                        candidate.setApplicantExperience(applicant.getInt("Experience"));

                        mApplicants.add(candidate);
                    }
                } else {
                    Log.e("MainActivity", "Error", e);
                }
            }
        });

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

        // Get the values from backend (Parse)
        updateUI(position);

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the next candidate and save the current's ratings
                ParseQuery<ParseObject> canQuery = ParseQuery.getQuery("");
                canQuery.whereEqualTo("Name",mApplicants.get(position).getApplicantName());
                canQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if( e == null){
                            object.put("RatingResume",3);
                            object.put("RatingProfile",4);
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if( e == null){
                                        if(position < mApplicants.size()) {
                                            position += 1;
                                            updateUI(position);
                                        }else{
                                            // Show a dialog
                                            Toast.makeText(MainActivity.this,
                                                    "That's it for today!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this,
                                                "There was a problem saving your ratings. " +
                                                        "Please try again !",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{

                        }
                    }
                });


            }
        });



    }

    public void updateUI(int position){
        Applicant applicant = mApplicants.get(position);
        String name = applicant.getApplicantName();
        String designation = applicant.getApplicantDesignation();
        String btech = applicant.getApplicantBtech();
        String mtech = applicant.getApplicantMtech();
        String phd = applicant.getApplicantPhd();
        int research = applicant.getApplicantResearch();
        int experience = applicant.getApplicantExperience();

        mName.setText(name);
        mDesig.setText(designation);
        mBTech.setText(btech);
        mMtech.setText(mtech);
        mPhd.setText(phd);
        mResearch.setText(research + " Research Papers");
        mExp.setText(experience+ " years of experience");
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
