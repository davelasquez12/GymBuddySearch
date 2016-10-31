package com.example.david.gymbuddy;

import android.widget.TextView;

public class Post
{
    private String mActivity, mDate, mTime, mComment, mLocation;
    private int mNumParticipants;

    public Post(String meetingDate, String meetingTime, String location, String activity, int numParticipants, String comment)
    {
        mActivity = activity;
        mDate = meetingDate;
        mTime = meetingTime;
        mLocation = location;
        mNumParticipants = numParticipants;
        mComment = comment;
    }

    public String getActivity()
    {
        return mActivity;
    }

    public String getMeetingDate()
    {
        return mDate;
    }

    public String getMeetingTime()
    {
        return mTime;
    }

    public String getLocation() {return mLocation;}

    public int getNumParticipants()
    {
        return mNumParticipants;
    }

    public String getComment()
    {
        return mComment;
    }
}
