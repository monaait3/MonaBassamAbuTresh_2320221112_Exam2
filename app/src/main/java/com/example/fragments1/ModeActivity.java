package com.example.fragments1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayoutMediator tabMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        // Set up the toolbar and Up button.
        Toolbar toolbar = findViewById(R.id.mode_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Modes");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Connect the three fragments to ViewPager2.
        viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        Button setModeButton = findViewById(R.id.button_set_mode);

        TabsAdapter adapter = new TabsAdapter(this);
        viewPager.setAdapter(adapter);

        // Connect tab titles with swipeable pages.
        String[] tabTitles = {"First", "Second", "Third"};

        tabMediator = new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> tab.setText(tabTitles[position])
        );

        tabMediator.attach();

        // Open the mode popup menu.
        setModeButton.setOnClickListener(view -> {
            showModeMenu(view);
        });

        // Handle the device Back button.
        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (viewPager.getCurrentItem() != 0) {
                            // From Second or Third, return to First.
                            viewPager.setCurrentItem(0, false);
                        } else {
                            // From First, return to MainActivity.
                            finish();
                        }
                    }
                }
        );
    }

    private void showModeMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);

        popupMenu.getMenuInflater().inflate(
                R.menu.mode_menu,
                popupMenu.getMenu()
        );

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.mode_light) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                );
                return true;
            }

            if (itemId == R.id.mode_night) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                );
                return true;
            }

            if (itemId == R.id.mode_system) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                );
                return true;
            }

            return false;
        });

        popupMenu.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // The toolbar arrow returns directly to the parent activity.
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    @Override
    protected void onDestroy() {
        if (tabMediator != null) {
            tabMediator.detach();
        }

        super.onDestroy();
    }
}