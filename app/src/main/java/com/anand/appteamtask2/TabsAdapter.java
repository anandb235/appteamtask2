package com.anand.appteamtask2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    public TabsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mNoOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RecipeFragment recipe = new RecipeFragment();
                return recipe;
            case 1:
                MenuFragment menu = new MenuFragment();
                return menu;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
