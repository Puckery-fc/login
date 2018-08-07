package sample.event.lisi.loginexample.fragments;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import sample.event.lisi.loginexample.R;

public class BaseSimpleCursorAdapter extends SimpleCursorAdapter {
    private LayoutInflater mInflater;
    private OneFragment.SQLiteListener listener;

    public BaseSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, OneFragment.SQLiteListener liteListener) {
        super(context, layout, c, from, to);
        this.listener = liteListener;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        LinearLayout ll = null;
        if (view == null) {
            ll = (LinearLayout) mInflater.inflate(R.layout.cursor_adapter_view, null);
        } else {
            ll = (LinearLayout) view;
        }

        final TextView tvName = (TextView) ll.findViewById(R.id.tv_name);
        final String name = cursor.getString(cursor.getColumnIndex("name"));
        tvName.setText(cursor.getString(cursor.getColumnIndex("name")));

        TextView tvSex = (TextView) ll.findViewById(R.id.tv_sex);
        final String sex = cursor.getString(cursor.getColumnIndex("sex"));
        tvSex.setText(sex);

        Button btnUpdate = (Button) ll.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("trace----");
                listener.update(name);
            }
        });
        Button btnDelete = (Button) ll.findViewById(R.id.btn_delete);
        Button btnInsert=(Button)ll.findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.insert("麻子","男");
            }
        });

    }
}
