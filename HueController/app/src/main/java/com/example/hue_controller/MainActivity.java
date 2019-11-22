package com.example.hue_controller;

import android.os.Bundle;

import com.example.hue_controller.ui.main.MultiEdit;
import com.example.hue_controller.ui.main.SingleEdit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SingleEdit singleFragment;
    private MultiEdit multiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);

        //Add the fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.singleFragment = new SingleEdit();
        this.multiFragment = MultiEdit.newInstance();

        adapter.AddFragment(singleFragment, getResources().getString(R.string.tab_text_1));
        adapter.AddFragment(multiFragment, getResources().getString(R.string.tab_text_2));

        //Add the setup
        this.viewPager.setAdapter(adapter);
        this.tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}