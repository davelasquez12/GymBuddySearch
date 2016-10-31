package com.example.david.gymbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


public class TimeDialogFragment extends android.support.v4.app.DialogFragment
{
    private TimePicker mTimePicker;
    private TimeDialogInteface mCallback;
    String format = "";

    public interface TimeDialogInteface
    {
        void setTimeFromDialog(String formattedTime);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time_picker, null);
        mTimePicker = (TimePicker) v.findViewById(R.id.time_picker);


        Date todaysDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);

        /*int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int timeofDay = calendar.get(Calendar.AM_PM);*/

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.set_time)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        int hour, minute;
                        if (Build.VERSION.SDK_INT >= 23 )
                            hour = mTimePicker.getHour();
                        else
                            hour = mTimePicker.getCurrentHour();

                        if(Build.VERSION.SDK_INT >= 23)
                            minute = mTimePicker.getMinute();
                        else
                            minute = mTimePicker.getCurrentMinute();

                        mCallback.setTimeFromDialog(getFormattedTime(hour, minute));

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    public String getFormattedTime(int hour, int min)
    {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        return new StringBuilder().append(hour).append(":").append(min).append(" ").append(format).toString();
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mCallback = (TimeDialogInteface) activity;
    }
}
