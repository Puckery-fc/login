package sample.event.lisi.loginexample.Activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import sample.event.lisi.loginexample.R;

public class DuihuaActivity extends ActionBarActivity {
    EditText ip;
    EditText editText;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duihua);
        ip=(EditText)findViewById(R.id.ip);
        editText=(EditText)findViewById(R.id.editReady);
        text=(TextView)findViewById(R.id.textReady);
        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });
        findViewById(R.id.sendReady).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
    Socket socket=null;
    BufferedWriter writer=null;
    BufferedReader reader=null;
    public void  connect(){
        AsyncTask<Void,String,Void> read=new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... arg0) {
                try {
                    socket=new Socket(ip.getText().toString(),12349);
                    writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    publishProgress("@success");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());

                }
                try{
                    String line;
                    while ((line=reader.readLine())!=null){
                        publishProgress(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return  null;
            }
            protected  void onProgressUpdate(String... values){
                if(values[0].equals("@success")){
                    Toast.makeText(DuihuaActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
                }
                text.append("别人说 ："+values[0]+"\n");
                super.onProgressUpdate(values);
            }
        };
        read.execute();
    }
    public  void  send(){
        try{
            text.append("我说："+editText.getText().toString()+"\n");
            writer.write(editText.getText().toString()+"\n");
            writer.flush();
            editText.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
