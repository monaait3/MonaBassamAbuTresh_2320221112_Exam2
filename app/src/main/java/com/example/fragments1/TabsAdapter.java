package com.example.fragments1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsAdapter extends FragmentStateAdapter {

    public TabsAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FirstFragment();

            case 1:
                return new SecondFragment();

            case 2:
                return new ThirdFragment();

            default:
                throw new IllegalArgumentException(
                        "Invalid tab position: " + position
                );
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}