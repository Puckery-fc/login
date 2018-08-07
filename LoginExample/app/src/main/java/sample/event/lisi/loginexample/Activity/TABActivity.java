package sample.event.lisi.loginexample.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import sample.event.lisi.loginexample.R;

public class TABActivity extends TabActivity {
    private TabHost m_tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //getTabHost返回的TabHost用于装载tabs
        m_tabHost = getTabHost();

        //add tabs,这里用于添加具体的Tabs,并用Tab触发相应的Activity
        addOneTab();
        addTwoTab();
        addThreeTab();
        addFourTab();
    }

    public void addOneTab(){
        Intent intent = new Intent();
        intent.setClass(TABActivity.this, BaseAdapterActivity.class);

        TabHost.TabSpec spec = m_tabHost.newTabSpec("One");
        spec.setIndicator(null, getResources().getDrawable(R.drawable.dialog1));
        spec.setContent(intent);
        m_tabHost.addTab(spec);
    }

    public void addTwoTab(){
        Intent intent = new Intent();
        intent.setClass(TABActivity.this, FriendsActivity.class);

        TabHost.TabSpec spec = m_tabHost.newTabSpec("Two");
        spec.setIndicator(null, getResources().getDrawable(R.drawable.friend));
        spec.setContent(intent);
        m_tabHost.addTab(spec);
    }
    public void addThreeTab(){
        Intent intent = new Intent();
        intent.setClass(TABActivity.this, SimpleAdatperActivity.class);

        TabHost.TabSpec spec = m_tabHost.newTabSpec("Three");
        spec.setIndicator(null, getResources().getDrawable(R.drawable.contacts));
        spec.setContent(intent);
        m_tabHost.addTab(spec);
    }
    public void addFourTab(){
        Intent intent = new Intent();
        intent.setClass(TABActivity.this, FriendsActivity.class);

        TabHost.TabSpec spec = m_tabHost.newTabSpec("Four");
        spec.setIndicator(null, getResources().getDrawable(R.drawable.settings));
        spec.setContent(intent);
        m_tabHost.addTab(spec);
    }
}
