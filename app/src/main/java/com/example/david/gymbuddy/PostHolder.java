package com.example.david.gymbuddy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    Context mContext;

    TextView mActivity, mDate, mTime, mLocation, mNumParticipants;

    public PostHolder(View itemView, Context context)
    {
        super(itemView);
        mContext = context;
        itemView.setOnClickListener(this);

        mActivity = (TextView)itemView.findViewById(R.id.post_item_activity_text_display);
        mDate = (TextView) itemView.findViewById(R.id.post_item_date_display);
        mTime = (TextView) itemView.findViewById(R.id.post_item_meeting_time);
        mNumParticipants = (TextView) itemView.findViewById(R.id.post_item_num_participants);
    }

    public void bindPostData(Post post)
    {
        mDate.setText(post.getMeetingDate());
        mTime.setText(post.getMeetingTime());
        //mLocation.setText(post.getLocation());
        mActivity.setText(post.getActivity());
        mNumParticipants.setText(String.valueOf(post.getNumParticipants()));
    }

    @Override
    public void onClick(View v)
    {

    }
}
