package sample.event.lisi.loginexample.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import sample.event.lisi.loginexample.R;

/**
 * Created by yinteli on 15/10/10.
 */
public class FriendsActivity extends Activity {
    TextView tvAccount, tvPSW;
    ListView listView;
    Spinner spinner;
    ArrayAdapter listViewAdapter, spinnerAdapter;

    private int spinnerSelectedPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list_layout);
        Intent intent = getIntent();
        init(intent);
    }

    private void init(Intent intent) {
        initTV(intent);
        initListView();
        initSpinner();
    }


    private void initListView() {
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FriendsActivity.this, "listview, 你选择了：" + listView.getAdapter().getItem(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String[] getData(int postion) {
        switch (postion) {
            case 0:
                return new String[]{};
            case 1:
                return getHLMCharacter();
            case 2:
                return getXYJCharacter();
            case 3:
                return geSYYCharacter();
        }
        return new String[]{};
    }

    private String[] geSYYCharacter() {
        return new String[]{"孔明", "刘备", "曹操"};
    }

    private String[] getXYJCharacter() {
        return new String[]{"孙悟空", "猪八戒", "沙僧"};
    }

    private String[] getHLMCharacter() {
        return new String[]{"贾宝玉", "林黛玉", "薛凤钗"};
    }

    private String[] getBookNames() {
        return new String[]{"请选择一本名著", "红楼梦", "西游记", "三国演义"};
    }

    private void initSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, getBookNames());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    Toast.makeText(FriendsActivity.this, "spinner, 你选择了：" + spinner.getAdapter().getItem(position).toString(), Toast.LENGTH_LONG).show();
                spinnerSelectedPostion = position;
                refreshListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void refreshListView() {
        listViewAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, getData(spinnerSelectedPostion));
        listView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();
    }

    private void initTV(Intent intent) {
        tvAccount = (TextView) findViewById(R.id.tv_account);
        tvAccount.setText(intent.getStringExtra(LoginActivity.ARG_STR_ACCOUNT));
        tvPSW = (TextView) findViewById(R.id.tv_psw);
        tvPSW.setText(intent.getStringExtra(LoginActivity.ARG_STR_PSW));
    }
}
