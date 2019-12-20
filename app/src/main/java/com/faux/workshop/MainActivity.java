package com.faux.workshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private com.faux.workshop.PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerAdapter = new com.faux.workshop.PagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new com.faux.workshop.PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AppliedWorkshops(), "Dashboard");
        adapter.addFragment(new AvailableWorkshops(), "Available");
        viewPager.setAdapter(adapter);
    }
}
    
    
