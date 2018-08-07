package sample.event.lisi.loginexample.Activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sample.event.lisi.loginexample.R;

public class DianwoActivity extends AppCompatActivity {

    private Button btnSend;
    private TextView tvShow;
    private  String response;
    private  final  int HTTP_200=200;
    private  final  String TAG_GET="HttpURLConnection";
    String path="http://reg.163.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianwo);
        init();
    }

    private void init() {
        btnSend=(Button)findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpURLTask().execute();
            }
        });
        tvShow=(TextView)findViewById(R.id.tv_show);
    }

    class  HttpURLTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try{
                tvShow.setText(new String((byte[])o,"UTF-8").trim());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            byte[] date=null;
            try{
                URL url=new URL(path);
                HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
                urlConn.setConnectTimeout(5*1000);
                urlConn.connect();
                if(urlConn.getResponseCode()==HTTP_200){
                    date=readStream(urlConn.getInputStream());
                    Log.i(TAG_GET, "Get方式请求成功，返回数据如下");
                    Log.i(TAG_GET,new String(date,"UTF-8"));
                }else{
                    Log.i(TAG_GET,"Get方式请求失败");
                }
                urlConn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return date;
        }

        private byte[] readStream(InputStream inputStream) {
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            byte[] buff=new byte[100];
            int rc=0;
            try{
                while ((rc=inputStream.read(buff,0,100))>0){
                    stream.write(buff,0,rc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] in2b=stream.toByteArray();
            return in2b;
        }
    }
}
