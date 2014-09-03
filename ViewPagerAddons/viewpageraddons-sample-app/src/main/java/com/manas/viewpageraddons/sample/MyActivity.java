package com.manas.viewpageraddons.sample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.manas.viewpageraddons.view.SlidingTabLayoutColor;

import java.util.ArrayList;


public class MyActivity extends Activity {

    ViewPager pager;
    SlidingTabLayoutColor slidingTabsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CustomPagerAdapter(getFragmentManager()));

        slidingTabsLayout = (SlidingTabLayoutColor) findViewById(R.id.coloredSlidingTabLayout);
        slidingTabsLayout.setViewPager(pager);
        slidingTabsLayout.setActionBar(getActionBar());
    }

    private static class CustomPagerAdapter extends FragmentPagerAdapter implements  SlidingTabLayoutColor.ColorProvider {

        ArrayList<Fragment> fragments;
        final String[] titles = new String[]{"Movies", "TV Shows", "Entertainment", "Sports", "Infotainment", "Music", "Comedy", "Thriller"};
        final int[] colors = new int[]{Color.parseColor("#0F9D58"), Color.parseColor("#3F5CA9"), Color.parseColor("#EF6C00"), Color.parseColor("#7E3794"), Color.parseColor("#55bada"), Color.parseColor("#0F9D58"), Color.parseColor("#7E3794"), Color.parseColor("#EF6C00")};

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<Fragment>();
            fragments.add(TestFragment.newInstance(1, colors[0]));
            fragments.add(TestFragment.newInstance(2, colors[1]));
            fragments.add(TestFragment.newInstance(3, colors[2]));
            fragments.add(TestFragment.newInstance(4, colors[3]));
            fragments.add(TestFragment.newInstance(5, colors[4]));
            fragments.add(TestFragment.newInstance(6, colors[5]));
            fragments.add(TestFragment.newInstance(7, colors[6]));
            fragments.add(TestFragment.newInstance(8, colors[7]));
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getPageColor(int pos) {
            return colors[pos];
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
