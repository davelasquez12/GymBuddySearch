package com.example.david.gymbuddy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreatePostFragment extends Fragment
{
    private Spinner mLocationDropdown;
    private Spinner mActivityDropdown;
    private Spinner mNumPeopleDropdown;
    private Button mSetDateButton, mSetTimeButton, mCreateButton;
    private EditText mCommentBox;

    public interface PostInterface
    {
        void addPost(Post post);
    }

    public CreatePostFragment()
    {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        mLocationDropdown = (Spinner) view.findViewById(R.id.location_choice_spinner);
        mActivityDropdown = (Spinner) view.findViewById(R.id.activity_choice_spinner);
        mNumPeopleDropdown = (Spinner) view.findViewById(R.id.number_people_spinner);
        mSetDateButton = (Button) view.findViewById(R.id.set_meeting_date_button);
        mSetTimeButton = (Button) view.findViewById(R.id.set_meeting_time_button);
        mCommentBox = (EditText) view.findViewById(R.id.comment_box_new_post);
        mCreateButton = (Button) view.findViewById(R.id.submit_new_post_button);

        String[] items = new String[]{"Choose Location", "Rec. Center", "Tennis Court","HPE1","HPE2","Track Field"};
        String[] activities = new String[]{"Choose Activity","Tennis","Basketball","Soccer","Badminton","Racquetball","Ping-Pong","Gym:Chest","Gym:Back","Gym:Biceps","Gym:Triceps","Gym:Leg"};
        String[] numberpeople = new String[]{"Choose #People", "1","2","3","4","5","6","7","8","9","10"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item, activities);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item, numberpeople);

        mLocationDropdown.setAdapter(adapter);
        mActivityDropdown.setAdapter(adapter2);
        mNumPeopleDropdown.setAdapter(adapter3);

        mSetDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                DateDialogFragment dialog = new DateDialogFragment();
                dialog.show(fm, "DateDialog");
                //Note: when "ok" is clicked in dialog, setDateFromDialog() is called
            }
        });

        mSetTimeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                TimeDialogFragment dialog = new TimeDialogFragment();
                dialog.show(fm, "TimeDialog");
            }
        });

        mCreateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PostInterface postBoardActivity = (PostInterface) getActivity();
                String meetingDate = mSetDateButton.getText().toString();
                String meetingTime = mSetTimeButton.getText().toString();
                String location = mLocationDropdown.getSelectedItem().toString();
                String activity = mActivityDropdown.getSelectedItem().toString();
                String numPeople = mNumPeopleDropdown.getSelectedItem().toString();
                String comment = mCommentBox.getText().toString();

                Post newPost = new Post(meetingDate, meetingTime, location, activity, Integer.parseInt(numPeople), comment);
                postBoardActivity.addPost(newPost);
                mLocationDropdown.setSelection(0);
                mActivityDropdown.setSelection(0);
                mNumPeopleDropdown.setSelection(0);
                mSetTimeButton.setText(R.string.set_time);
                mSetDateButton.setText(R.string.set_date);
                mCommentBox.setText("");
            }
        });

        return view;
    }

    public void setDate(String date)
    {
        mSetDateButton.setText(date);
    }

    public void setTime(String time)
    {
        mSetTimeButton.setText(time);
    }
}
