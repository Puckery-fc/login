package sample.event.lisi.loginexample.Activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import sample.event.lisi.loginexample.MyPagerAdapter;
import sample.event.lisi.loginexample.R;

public class ViewPagerActivity extends Activity {

    LayoutInflater inflater;
    View first;
    View second;
    ViewPager viewPager;
    PagerTabStrip tabStrip;
    List<View> viewList = new ArrayList<View>();
    List<String> viewTitle = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        init();
    }


    public void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        inflater = this.getLayoutInflater().from(this);
        first = inflater.inflate(R.layout.view_one, null);
        second = inflater.inflate(R.layout.view_two, null);

        viewList.add(first);
        viewTitle.add("first");
        viewList.add(second);
        viewTitle.add("second");
        viewPager.setAdapter(new MyPagerAdapter(viewList, viewTitle));
    }
}


