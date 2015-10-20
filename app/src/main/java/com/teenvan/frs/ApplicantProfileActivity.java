package com.teenvan.frs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import org.w3c.dom.Text;

public class ApplicantProfileActivity extends AppCompatActivity {
    // Declaration of member variables
    private Button viewResumeButton;
    private TextView mApplicantName, mApplicantDepartment, btechCollege, btechCGPA, mtechCollege,
    mtechCGPA, researchPapers, expereince;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_profile);



        // Referencing the UI elements
        mApplicantDepartment = (TextView)findViewById(R.id.departmentText);
        mApplicantName = (TextView)findViewById(R.id.applicantNameProfileText);
        btechCollege = (TextView)findViewById(R.id.btechText);
        btechCGPA = (TextView)findViewById(R.id.cgpaText);
        mtechCollege = (TextView)findViewById(R.id.mtechText);
        mtechCGPA = (TextView)findViewById(R.id.mcgpaText);
        researchPapers = (TextView)findViewById(R.id.numberResearchText);
        expereince = (TextView)findViewById(R.id.numberExpereinceText);



        viewResumeButton = (Button)findViewById(R.id.viewResumeButton);

        viewResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
