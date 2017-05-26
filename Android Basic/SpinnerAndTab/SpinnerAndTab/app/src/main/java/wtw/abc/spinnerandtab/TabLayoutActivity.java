package wtw.abc.spinnerandtab;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;
    int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab.post(new Runnable() {
            @Override
            public void run() {
                tab.setupWithViewPager(viewPager);
            }
        });
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Call";
                case 1:
                    return "Chat";
                case 2:
                    return "contact";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CallFragment();
                case 1:
                    return new ChatFragment();
                case 2:
                    return new ContactFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
