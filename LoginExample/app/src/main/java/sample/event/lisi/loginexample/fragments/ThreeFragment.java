package sample.event.lisi.loginexample.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sample.event.lisi.loginexample.R;

public class ThreeFragment extends Fragment{
    private EditText etContent;
    private TextView tvShow;
    private Button btnSave,btnShow;

    private  byte[] buffer;
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        etContent = (EditText) view.findViewById(R.id.et_content);
        tvShow = (TextView) view.findViewById(R.id.tv_show);

        btnSave = (Button) view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeSDcard(etContent.getText().toString().trim());
            }
        });
        btnShow = (Button) view.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readSDcard();
            }
        });
        return view;
    }
    private void writeSDcard(String str) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try{
                File sdDire=new File(Environment.getExternalStorageDirectory().getCanonicalPath(),"test2.text");
                FileOutputStream fos=new FileOutputStream(sdDire);
                fos.write(str.getBytes());
                fos.close();
                Toast.makeText(getActivity(), "数据保存在text.txt文件了", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(getActivity(),"没有sd卡",Toast.LENGTH_LONG).show();
        }
    }
    private void readSDcard() {
        StringBuilder sb=new StringBuilder();
        byte[] buffer=new byte[1024];
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try{
                File file=new File(Environment.getExternalStorageDirectory().getCanonicalPath()+"/text2.txt");
                if(file.exists()){
                    FileInputStream fis=new FileInputStream(file);
                    int hasRead=0;
                    while ((hasRead=fis.read(buffer))!=1){
                        sb.append(new String(buffer,0,hasRead));
                    }
                    tvShow.setText(sb.toString());
                }
                else{
                    Toast.makeText(getActivity(),"该目录文件不存在",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Inflate the layout for this fragment

}


