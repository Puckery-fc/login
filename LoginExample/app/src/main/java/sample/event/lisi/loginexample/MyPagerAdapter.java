package sample.event.lisi.loginexample;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
    List<View> viewList;
    List<String> viewTitle;

    public MyPagerAdapter(List<View> viewList, List<String> viewTitle) {
        this.viewList = viewList;
        this.viewTitle = viewTitle;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewTitle.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

}
