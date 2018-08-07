package sample.event.lisi.loginexample.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sample.event.lisi.loginexample.FriendModel;
import sample.event.lisi.loginexample.R;

/**
 * Created by yinteli on 15/10/22.
 */
public class BaseAdapterActivity extends Activity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_adapter_xml);

        listView = (ListView) findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter(this, getData()) ;
        listView.setAdapter(adapter);
    }

    private List getData()
    {
        List<FriendModel> list = new ArrayList<FriendModel>();
        FriendModel model = new FriendModel();
        model.setFriendName("樱木花道");
        model.setFriendHeadImage(R.drawable.yinmuhuadao);
        model.setFriendDes("头碰篮板的男人");
        list.add(model);

        FriendModel model2 = new FriendModel();
        model2.setFriendName("流川枫");
        model2.setFriendHeadImage(R.drawable.liuchuanfeng);
        model2.setFriendDes("帅到没朋友");
        list.add(model2);

        FriendModel model3 = new FriendModel();
        model3.setFriendName("赤木");
        model3.setFriendHeadImage(R.drawable.chimu);
        model3.setFriendDes("内线扛把子");
        list.add(model3);

        FriendModel model4 = new FriendModel();
        model4.setFriendName("赤木晴子");
        model4.setFriendHeadImage(R.drawable.chimuqingzi);
        model4.setFriendDes("万人迷");
        list.add(model4);
        return list;
    }

    class CustomAdapter extends BaseAdapter{

        private List mData;
        private Context mContext;

        public CustomAdapter(Context context, List mData)
        {
            this.mData = mData;
            this.mContext = context;
        }
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mContext,R.layout.friend_list_base_adapter_view, null);

            TextView name = (TextView)view.findViewById(R.id.tv_name);
            name.setText(((FriendModel)mData.get(position)).getFriendName());

            TextView des = (TextView)view.findViewById(R.id.tv_des);
            des.setText(((FriendModel)mData.get(position)).getFriendDes());

            ImageView headIcon = (ImageView)view.findViewById(R.id.iv_head);
            headIcon.setBackgroundResource(((FriendModel) mData.get(position)).getFriendHeadImage());
            return view;
        }
    }

}
