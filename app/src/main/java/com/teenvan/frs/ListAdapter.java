package com.teenvan.frs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navneet on 27/09/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    // Declaration of member variables
    private List<String> mVacancyTitle, mVacancyPosted, mVacancyDeadline;
    private Context mContext;

    public ListAdapter(Context context,
                       ArrayList<String> mVacancyTitle,ArrayList<String> mVacancyPosted
                            ,ArrayList<String> mVacancyDeadline) {
        super();
        this.mVacancyDeadline = mVacancyDeadline;
        this.mVacancyPosted = mVacancyPosted;
        this.mVacancyTitle = mVacancyTitle;
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.vacancyTitle.setText(mVacancyTitle.get(i));
        viewHolder.vacancyPosted.setText(mVacancyPosted.get(i));
        viewHolder.vacancyDeadline.setText(mVacancyDeadline.get(i));
        viewHolder.vacancyViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the view
                Intent intent = new Intent(mContext, VacancyViewActivity.class);
                intent.putExtra("Title",mVacancyTitle.get(i));
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
        public TextView vacancyPosted;
        public TextView vacancyDeadline;
        public Button vacancyViewButton;

        public ViewHolder(View itemView) {
            super(itemView);
            vacancyTitle = (TextView)itemView.findViewById(R.id.designationText);
            vacancyPosted = (TextView)itemView.findViewById(R.id.postedValue);
            vacancyDeadline = (TextView)itemView.findViewById(R.id.deadlineValue);
            vacancyViewButton = (Button)itemView.findViewById(R.id.viewButton);
        }
    }
}
