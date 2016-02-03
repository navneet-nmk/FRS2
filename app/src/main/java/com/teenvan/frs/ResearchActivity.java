package com.teenvan.frs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ResearchActivity extends AppCompatActivity {
    // Declaration of member variables
    private EditText mPhDSpecialization , mPInstitute , mNumberResearch, mExpereince,mCurrent;
    private Button mConfirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_research);

        Typeface tv = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Regular.otf");

        // Referencing the UI Elements
        mPhDSpecialization = (EditText)findViewById(R.id.specializationEditText);
        mPInstitute = (EditText)findViewById(R.id.instituteEditTextP);
        mNumberResearch = (EditText)findViewById(R.id.numberResearchEditText);
        mExpereince = (EditText)findViewById(R.id.numberExpereinceEditText);
        mCurrent = (EditText)findViewById(R.id.currentEditText);
        mConfirmButton = (Button)findViewById(R.id.confirmApplicationButton);

        mPhDSpecialization.setTypeface(tv);
        mPInstitute.setTypeface(tv);
        mNumberResearch.setTypeface(tv);
        mExpereince.setTypeface(tv);
        mCurrent.setTypeface(tv);
        mConfirmButton.setTypeface(tv);

        // Click event listeners
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressBarIndeterminateVisibility(true);
                // Get the text values
                String special = mPhDSpecialization.getText().toString();
                String insti = mPInstitute.getText().toString();
                String research = mNumberResearch.getText().toString();
                String experience = mExpereince.getText().toString();
                String current = mCurrent.getText().toString();

                //Check for errors
                if(special.isEmpty() || insti.isEmpty() || research.isEmpty() ||
                        experience.isEmpty() || current.isEmpty()){
                    setProgressBarIndeterminateVisibility(false);
                    Toast.makeText(ResearchActivity.this,"Please enter all details",
                            Toast.LENGTH_SHORT).show();
                }else{
                    ParseUser user = ParseUser.getCurrentUser();
                    if(user == null){
                        setProgressBarIndeterminateVisibility(false);
                        Toast.makeText(ResearchActivity.this, "Please create an account",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        user.put("Specialization",special);
                        user.put("PhDInstitute",insti);
                        user.put("ResearchPapers",research);
                        user.put("CurrentPosition",current);
                        user.put("Experience",experience);
                        user.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                setProgressBarIndeterminateVisibility(false);
                                if( e== null){
                                    // Success
                                    Intent intent = new Intent(ResearchActivity.this,
                                            VacancyActivity.class);
                                    startActivity(intent);
                                }else{
                                    Log.e("ResearchActivity","Error",e);
                                    Toast.makeText(ResearchActivity.this,"Please try again",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            }
        });

    }

}
