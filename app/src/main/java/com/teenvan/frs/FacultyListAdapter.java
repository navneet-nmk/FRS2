package com.teenvan.frs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import oauth.signpost.http.HttpResponse;

/**
 * Created by navneet on 27/09/15.
 */
public class FacultyListAdapter extends RecyclerView.Adapter<FacultyListAdapter.ViewHolder> {

    // Declaration of member variables
    private List<String> mApplicantName, mDesignation, mRating;
    private Context mContext;

    public FacultyListAdapter(Context context,
                              ArrayList<String> mApplicantName, ArrayList<String> mDesignation
            , ArrayList<String> mRating) {
        super();
        this.mApplicantName = mApplicantName;
        this.mDesignation = mDesignation;
        this.mRating = mRating;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.faculty_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FacultyListAdapter.ViewHolder holder, final int i) {
        Typeface tv = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/Montserrat-Regular.otf");
        holder.applicantName.setText(mApplicantName.get(i));
        holder.applicantDesig.setText(mDesignation.get(i));
        holder.applicantRating.setText(mRating.get(i));
        holder.applicantName.setTypeface(tv);
        holder.applicantDesig.setTypeface(tv);
        holder.applicantRating.setTypeface(tv);
        holder.mAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mAccept.setEnabled(false);
                holder.mReject.setEnabled(false);
                // Accept the candidate and send confirmation email
                Toast.makeText(mContext,mApplicantName.get(i)+" has been selected.",
                        Toast.LENGTH_SHORT).show();
                ParseQuery pushQuery = ParseInstallation.getQuery();
                pushQuery.whereEqualTo("User", mApplicantName.get(i));

// Send push notification to query
                ParsePush push = new ParsePush();
                push.setQuery(pushQuery); // Set our Installation query
                push.setMessage("You have been selected for the position of Professor,CSE");
                push.sendInBackground();
            }
        });
        holder.mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mReject.setEnabled(false);
                holder.mAccept.setEnabled(false);
                Toast.makeText(mContext,mApplicantName.get(i)+" has been rejected",
                        Toast.LENGTH_SHORT).show();
                mApplicantName.remove(i);
                mDesignation.remove(i);
                mRating.remove(i);
                // Delete from the parse database
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mApplicantName.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView applicantName, applicantDesig, applicantRating;
        public Button mAccept, mReject;

        public ViewHolder(View itemView) {
            super(itemView);
            applicantName = (TextView) itemView.findViewById(R.id.applicantNameText);
            applicantDesig = (TextView) itemView.findViewById(R.id.designantionAppText);
            applicantRating = (TextView) itemView.findViewById(R.id.ratingText);
            mAccept = (Button) itemView.findViewById(R.id.acceptButton);

            mReject = (Button) itemView.findViewById(R.id.cancelButton);
        }
    }




}
