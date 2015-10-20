package com.teenvan.frs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navneet on 27/09/15.
 */
public class ApplicantListAdapter extends RecyclerView.Adapter<ApplicantListAdapter.ViewHolder> {

    // Declaration of member variables
    private List<String> mVacancyTitle, mApplicant;
    private Context mContext;

    public ApplicantListAdapter(Context context,
                                ArrayList<String> mVacancyTitle, ArrayList<String> mApplicant) {
        super();
        this.mApplicant = mApplicant;
        this.mVacancyTitle = mVacancyTitle;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.applicant_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ApplicantListAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.vacancyTitle.setText(mVacancyTitle.get(i));
        viewHolder.mApplicantName.setText(mApplicant.get(i));
        viewHolder.vacancyViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the view
                Intent intent = new Intent(mContext, ApplicantProfileActivity.class);
                intent.putExtra("Name",mApplicant.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVacancyTitle.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView vacancyTitle;
        public TextView mApplicantName;
        public Button vacancyViewButton;

        public ViewHolder(View itemView) {
            super(itemView);
            vacancyTitle = (TextView)itemView.findViewById(R.id.designantionAppText);
            mApplicantName = (TextView)itemView.findViewById(R.id.applicantNameText);
            vacancyViewButton = (Button)itemView.findViewById(R.id.viewButton);
        }
    }
}
