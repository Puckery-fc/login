package sample.event.lisi.loginexample.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sample.event.lisi.loginexample.MyPagerAdapter;
import sample.event.lisi.loginexample.R;

public class SimpleTabsActivity extends Activity {

    LayoutInflater inflater;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View first;
    View second;
    List<View> viewList = new ArrayList<View>();
    List<String> viewTitle = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tabs);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        init();


    }

    private void init()
    {
        initViews();
        initViewPager();
        initTabLayout();
        setupTabIcons();
    }

    private void initViewPager()
    {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
    }

    private void initTabLayout()
    {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.dialog,
                R.drawable.friend,
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void initViews() {
        inflater = this.getLayoutInflater().from(this);
        first = inflater.inflate(R.layout.view_one, null);
        second = inflater.inflate(R.layout.view_two, null);
    }

    private void setupViewPager(ViewPager viewPager) {
        viewList.add(first);
        viewTitle.add("1");
        viewList.add(second);
        viewTitle.add("2");
        MyPagerAdapter adapter = new MyPagerAdapter(viewList, viewTitle);

        viewPager.setAdapter(adapter);
    }
}
