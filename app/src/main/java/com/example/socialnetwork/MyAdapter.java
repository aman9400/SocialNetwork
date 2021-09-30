package com.example.socialnetwork;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class MyAdapter extends FragmentPagerAdapter {
    final Context context;
    final int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NotificationFrag();
            case 1:
                return new VideoFrag();
            case 2:
                return new GiftFrag();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}