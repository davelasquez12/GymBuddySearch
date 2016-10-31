package com.example.david.gymbuddy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private final String[] mTabTitles = {"Postboard", "New Post"};
    private final static int NUM_TABS = 2;
    private Fragment[] registeredFragments = new Fragment[2];

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return new ViewPostsFragment();
            case 1:
                return new CreatePostFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return NUM_TABS;
    }

    @Override
    public int getItemPosition(Object object)
    {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mTabTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments[position] = fragment;
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        registeredFragments[position] = null;
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(int position)
    {
        return registeredFragments[position];
    }
}
