package com.teenvan.frs;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.logging.Handler;

public class VacancyViewActivity extends AppCompatActivity {
    // Declaration of member variables
    private TextView mJobtitle, mJobDescription;
    private Button mApplyButton;
    private String JobTitle, JobPosted, Deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JobTitle = getIntent().getStringExtra("Title");
        JobPosted = getIntent().getStringExtra("PostDate");
        Deadline = getIntent().getStringExtra("Deadline");
        setContentView(R.layout.activity_vacancy_view);
        // Referencing the UI elements
        mJobDescription = (TextView)findViewById(R.id.jobDescrip);
        mJobtitle = (TextView)findViewById(R.id.jobTitle);
        mApplyButton = (Button)findViewById(R.id.applyButton);

        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Show a dialog box
                final Dialog dialog = new Dialog(VacancyViewActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog);
                Button confirmButton = (Button)dialog.findViewById(R.id.confirmButtonD);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Save to parse
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Vacancies");
                        query.whereEqualTo("Title",JobTitle);
                        query.whereEqualTo("Date",JobPosted);
                        query.whereEqualTo("Deadline",Deadline);
                        query.getFirstInBackground(new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject parseObject, ParseException e) {
                                if( e == null){
                                    ParseObject object = new ParseObject("JobApplicant");
                                    object.put("Department",parseObject.getString("Department"));
                                    object.put("Title",parseObject.getString("Title"));
                                    object.put("Id",parseObject.getObjectId());
                                    object.put("ApplicantName",ParseUser.getCurrentUser().
                                            getUsername());
                                    object.put("ApplicantId",ParseUser.getCurrentUser().
                                            getObjectId());
                                    object.saveEventually(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if( e == null){
                                                dialog.dismiss();
                                                Toast.makeText(VacancyViewActivity.this,
                                                        "Successfully applied !",
                                                        Toast.LENGTH_SHORT).show();
                                                android.os.Handler handler = new
                                                        android.os.Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent intent = new Intent(
                                                                VacancyViewActivity.this,
                                                                VacancyActivity.class);
                                                        startActivity(intent);
                                                    }
                                                },500);
                                            }else{
                                                dialog.dismiss();
                                                Toast.makeText(VacancyViewActivity.this,
                                                        "Please try again later",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else{

                                }
                            }
                        });
                        dialog.show();

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vacancy_view, menu);
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
