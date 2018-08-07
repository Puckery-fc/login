package sample.event.lisi.loginexample.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sample.event.lisi.loginexample.R;


public class SimpleAdatperActivity extends ActionBarActivity {

    SimpleAdapter sa;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adatper);

        listView = (ListView)findViewById(R.id.listview);
        sa = new SimpleAdapter(this,getData(),R.layout.simple_adapter_layout, new String[]{"名字","图片"},new int[]{R.id.tv_name,R.id.iv_image});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SimpleAdatperActivity.this, "click: " + ((HashMap) listView.getAdapter().getItem(position)).get("名字"), Toast.LENGTH_LONG).show();
            }
        });
    }


    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<>();
        map.put("名字","林黛玉");
        mList.add(map);

        map = new HashMap<>();
        map.put("名字","贾宝玉");
        mList.add(map);
        return mList;
    }


}
