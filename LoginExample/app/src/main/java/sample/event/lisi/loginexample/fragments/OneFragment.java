package sample.event.lisi.loginexample.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import sample.event.lisi.loginexample.R;


public class OneFragment extends Fragment {
    private ListView listview;
    SimpleCursorAdapter simpleCursorAdapter;
    private View view;
    private BaseSqliteDBHelper dbHelper;
    private SQLiteDatabase dbWrite;
    private Cursor cursor;
    public OneFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new BaseSqliteDBHelper(getActivity());
        dbWrite = dbHelper.getWritableDatabase();
        insertSQL(dbWrite);
        initListView();
    }
    private void initListView() {
        final SQLiteDatabase dbReader = dbHelper.getReadableDatabase();
        cursor = queryData(dbReader);
        listview = (ListView) view.findViewById(R.id.listview);
        simpleCursorAdapter = new BaseSimpleCursorAdapter(getActivity(), R.layout.cursor_adapter_view, cursor, new String[]{"name", "sex"}, new int[]{R.id.tv_name, R.id.tv_sex}, new SQLiteListener() {
            @Override
            public void update(String name) {
                updateSQL(name);
                cursor = queryData(dbReader);
                simpleCursorAdapter.changeCursor(cursor);
                traverse();
            }
            @Override
            public void delete() {

            }
            public void  insert(String name,String sex){
                insertSQL(name,sex);
                cursor=queryData(dbReader);
                simpleCursorAdapter.changeCursor(cursor);
            }
        });
        listview.setAdapter(simpleCursorAdapter);
    }
    private Cursor queryData(SQLiteDatabase dbReader) {
        dbReader = dbHelper.getReadableDatabase();
        Cursor cursor = dbReader.query("user", null, null, null, null, null, null);
        return cursor;
    }

    private void updateSQL(String name) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put("name", "乔丹");
        dbWrite = dbHelper.getWritableDatabase();
        dbWrite.update("user", cvUpdate, "name=?", new String[]{name});
        dbWrite.close();
    }

    private void deleteSQL() {

    }

    private void traverse() {
        if (cursor != null)
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                System.out.println(String.format("trace---name=%s,sex=%s", name, sex));
            }
    }
    public interface SQLiteListener {
        void update(String name);

        void delete();
        void  insert(String name,String sex);
    }
    private void insertSQL(SQLiteDatabase dbWrite) {
        ContentValues cv = new ContentValues();
        cv.put("name", "张三");
        cv.put("sex", "男");
        dbWrite.insert("user", null, cv);
        cv.put("name", "李四");
        cv.put("sex", "男");
        dbWrite.insert("user", null, cv);
        dbWrite.close();
    }
    private  void insertSQL(String name,String sex){
        dbWrite=dbHelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("sex",sex);
        dbWrite.insert("user",null,cv);
        dbWrite.close();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;
    }
}

