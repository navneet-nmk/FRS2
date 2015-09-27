package com.teenvan.frs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class VacancyActivity extends AppCompatActivity {
    // Declaration of member variables
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mVacancyTitle;
    private ArrayList<String> mVacancyPosted;
    private ArrayList<String> mVacancyDeadline;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);

        //Referencing the UI elements
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //Get the data from Parse
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Vacancies");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                mVacancyTitle = new ArrayList<String>();
                mVacancyPosted = new ArrayList<String>();
                mVacancyDeadline = new ArrayList<String>();
                if( e==null){
                    // Get the list items
                    for(ParseObject object: list){
                        String mTitle = object.getString("Title");
                        String mPosted = object.getString("Date");
                        String mDeadline = object.getString("Deadline");
                        mVacancyTitle.add(mTitle);
                        mVacancyPosted.add(mPosted);
                        mVacancyDeadline.add(mDeadline);
                    }
                    mAdapter = new ListAdapter(VacancyActivity.this,mVacancyTitle,
                            mVacancyPosted,mVacancyDeadline);
                    mRecyclerView.setAdapter(mAdapter);
                }else{
                    Log.e("VacancyActivity","Error",e);
                    Toast.makeText(VacancyActivity.this,"There was some error",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vacancy, menu);
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
