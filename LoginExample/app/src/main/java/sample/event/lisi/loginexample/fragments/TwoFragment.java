package sample.event.lisi.loginexample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sample.event.lisi.loginexample.R;


public class TwoFragment extends Fragment{
    private EditText etContent;
    private TextView tvShow;
    private Button btnSave,btnShow;
    private FileOutputStream fos;
    private FileInputStream fis;
    private  byte[] buffer;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two,container,false);
        etContent=(EditText)view.findViewById(R.id.et_content);
        tvShow=(TextView)view.findViewById(R.id.tv_show);

        btnSave=(Button)view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fos = getActivity().openFileOutput("test", Context.MODE_PRIVATE);
                    fos.write(etContent.getText().toString().trim().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnShow=(Button)view.findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    fis=getActivity().openFileInput("test");
                    buffer=new byte[1024];
                    StringBuilder sb=new StringBuilder();
                    int hasRead=0;
                    while ((hasRead=fis.read(buffer))!=1){
                        sb.append(new String(buffer,0,hasRead));
                    }
                    fis.close();
                    tvShow.setText(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

}
