package com.example.david.gymbuddy;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PostBoardActivity extends AppCompatActivity implements CreatePostFragment.PostInterface,
        DateDialogFragment.DateDialogInterface, TimeDialogFragment.TimeDialogInteface
{
    private Toolbar mPostboardToolbar;
    private ViewPager mPostboardVP;
    private TabLayout mTabLayout;
    private ViewPagerAdapter mVPAdapter;
    private List<Post> mPostList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postboard_activity);

        mPostboardToolbar = (Toolbar) findViewById(R.id.postboard_toolbar);
        mPostboardVP = (ViewPager) findViewById(R.id.postboard_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.postboard_tablayout);

        assert mTabLayout != null;
        setSupportActionBar(mPostboardToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int pagerTab = getIntent().getIntExtra("PagerTabNumber", 0);
        mVPAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPostboardVP.setAdapter(mVPAdapter);
        mTabLayout.setupWithViewPager(mPostboardVP);
        mPostboardVP.setCurrentItem(pagerTab);
    }

    public List<Post> getPostList()
    {
        return mPostList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_postboard, menu);
        return true;
    }

    @Override
    public void addPost(Post post)
    {
        mPostList.add(post);
        ViewPostsFragment viewPostsFragment = (ViewPostsFragment) mVPAdapter.getFragment(0);
        viewPostsFragment.updatePostList(mPostList);
        viewPostsFragment.setupRVAdapter();
        mPostboardVP.setCurrentItem(0);
    }

    @Override
    public void setDateFromDialog(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d y", Locale.getDefault());
        CreatePostFragment createPostFragment = (CreatePostFragment) mVPAdapter.getFragment(1);
        createPostFragment.setDate(sdf.format(date));
    }

    @Override
    public void setTitle(CharSequence title)
    {
        super.setTitle(title);
    }

    @Override
    public void setTimeFromDialog(String formattedTime)
    {
        CreatePostFragment createPostFragment = (CreatePostFragment) mVPAdapter.getFragment(1);
        createPostFragment.setTime(formattedTime);
    }
}
