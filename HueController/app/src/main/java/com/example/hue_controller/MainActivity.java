package com.example.hue_controller;

import android.os.Bundle;

import com.example.hue_controller.ui.main.SettingsFragment;
import com.example.hue_controller.ui.main.SingleEdit;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SingleEdit singleFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tabLayout = findViewById(R.id.tabs);
        this.viewPager = findViewById(R.id.view_pager);

        //Add the fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.singleFragment = new SingleEdit();
        this.settingsFragment = new SettingsFragment();
        adapter.AddFragment(singleFragment, getResources().getString(R.string.tab_text_1));
        adapter.AddFragment(settingsFragment, getResources().getString(R.string.tab_text_2));

        //Add the setup
        this.viewPager.setAdapter(adapter);
        this.tabLayout.setupWithViewPager(viewPager);

        Connection connection = new Connection(this, this.singleFragment, this.settingsFragment);
        Model.getInstance().init(this, connection);
        connection.getLamps();
    }
}