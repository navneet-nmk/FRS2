package com.teenvan.frs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EducationalActivity extends AppCompatActivity {
    // Declaration of member variables
    private EditText bDivision, bInstitute, bPerc, bYear, mDivision, mInstitute, mPerc ,mYear;
    private Button mProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational);

        // Referencing the UI elements
        bDivision = (EditText)findViewById(R.id.divisionEditText);
        bInstitute = (EditText)findViewById(R.id.instituteEditTextB);
        bPerc = (EditText)findViewById(R.id.percEditText);
        bYear = (EditText)findViewById(R.id.yearEditText);
        mDivision = (EditText)findViewById(R.id.mdivisionEditText);
        mInstitute = (EditText)findViewById(R.id.minstituteEditTextB);
        mPerc = (EditText)findViewById(R.id.mpercEditText);
        mYear = (EditText)findViewById(R.id.myearEditText);
        mProceed = (Button)findViewById(R.id.proceedButton);

        // Click event listener
        mProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the strings
                String btdivision = bDivision.getText().toString();
                String btInstitute = bInstitute.getText().toString();
                String btPerc = bPerc.getText().toString();
                String btYear = bYear.getText().toString();

                String mtDivision = mDivision.getText().toString();
                String mtInstitute = mInstitute.getText().toString();
                String mtPerc = mPerc.getText().toString();
                String mtYear =mYear.getText().toString();

                // Checking for errors
                if( btdivision.isEmpty() || btInstitute.isEmpty() || btPerc.isEmpty()
                        || btYear.isEmpty() || mtDivision.isEmpty() ||
                        mtInstitute.isEmpty() || mtPerc.isEmpty() || mtYear.isEmpty()){
                    Toast.makeText(EducationalActivity.this, "Please enter all the details " +
                            "accurately",Toast.LENGTH_SHORT).show();
                }else{
                    // Save it to the parse user
                    ParseUser user = ParseUser.getCurrentUser();
                    if(user ==null){
                        Toast.makeText(EducationalActivity.this,"Please create an account",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        user.put("BtechDivision",btdivision);
                        user.put("BtechInstitute",btInstitute);
                        user.put("BtechPercentage",btPerc);
                        user.put("BtechYear",btYear);
                        user.put("MtechDivision",mtDivision);
                        user.put("MtechInstitute",mtInstitute);
                        user.put("MtechPercentage",mtPerc);
                        user.put("MtechYear",mtYear);
                        user.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if( e == null){
                                    Intent intent = new Intent(EducationalActivity.this,
                                            ResearchActivity.class);
                                    startActivity(intent);
                                }else{
                                    Log.e("Error","Error",e);
                                    Toast.makeText(EducationalActivity.this, "Please try again",
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
