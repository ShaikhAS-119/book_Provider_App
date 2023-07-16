package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numofTabs;

    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        numofTabs=tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:

                return new Bookdetail();
            case 1:
                return new Pdfdetail();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numofTabs;
    }

}

