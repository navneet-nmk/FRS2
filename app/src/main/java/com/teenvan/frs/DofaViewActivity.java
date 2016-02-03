package com.teenvan.frs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DofaViewActivity extends AppCompatActivity {
    // Declaration of member variables
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mRating;
    private ArrayList<String> mName, mDesignation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dofa_view);

        // Referencing the UI elements
        mRecyclerView = (RecyclerView)findViewById(R.id.facultyList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the Shortlisted candidates
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shortlisted");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if( e == null){
                    mRating = new ArrayList<String>();
                    mName = new ArrayList<String>();
                    mDesignation = new ArrayList<String>();
                    for(ParseObject a : list){
                        mRating.add(a.getNumber("Rating").toString());
                        mName.add(a.getString("Name"));
                        mDesignation.add(a.getString("Designation"));

                    }
                    FacultyListAdapter adapter = new FacultyListAdapter
                            (DofaViewActivity.this,mName,mDesignation,mRating);
                    mRecyclerView.setAdapter(adapter);

                }else{

                }
            }
        });


    }

}
