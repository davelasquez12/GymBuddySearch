package com.example.david.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button mProfileSettingsButton;
    Button mViewPostsButton;
    Button mCreateNewPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProfileSettingsButton = (Button)findViewById(R.id.profile_setting);
        mViewPostsButton = (Button) findViewById(R.id.view_posts_button);
        mCreateNewPostButton = (Button) findViewById(R.id.create_new_post_button);

        mProfileSettingsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });

        mViewPostsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, PostBoardActivity.class);
                intent.putExtra("PagerTabNumber", 0);
                startActivity(intent);
            }
        });

        mCreateNewPostButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, PostBoardActivity.class);
                intent.putExtra("PagerTabNumber", 1);
                startActivity(intent);
            }
        });

    }
}
