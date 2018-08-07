package sample.event.lisi.loginexample.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sample.event.lisi.loginexample.R;


public class LoginActivity extends ActionBarActivity {

    EditText etAccount, etPSW;

   private Button btnLogin,btnDianwo,btnLt;
   private  MyReceiver receiver;

    public  final  static  String ACTION_NOTIFICATION="android.action.ACTION_NOTIFICATION";
    TextView tvRegister, tvForgetPSW,tvCheckUpdate;
    public static final String ARG_STR_ACCOUNT = "ARG_STR_ACCOUNT";
    public static final String ARG_STR_PSW = "ARG_STR_PSW";
    public static final String ARG_STR_RESPONSE="ARG_STR_RESPONSE";

    private  final  int HTTP_200=200;
    private  final  String TAG_GET="HttpURLConnection";
    String path="http://reg.163.com/logins.jsp?id=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        receiver =  new MyReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,new IntentFilter(ACTION_NOTIFICATION));
    }

    protected  void  onPause(){
     super.onPause();

     unregisterReceiver(receiver);
 }
    private void updateFromRegister(Intent intent)
    {
        etAccount.setText(intent.getStringExtra(RegisterActivity.ARG_ACCOUNT));
        etPSW.setText(intent.getStringExtra(RegisterActivity.ARG_PASSWORD));
    }

    private void init()
    {
        initEditText();
        initButton();
        initTextView();
        updateFromRegister(getIntent());

    }

    private void initEditText()
    {
        etAccount = (EditText)findViewById(R.id.et_account);

        etPSW = (EditText)findViewById(R.id.et_psw);
    }

    private void initButton()
    {
        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,IconTabsActivity.class);
                intent.putExtra(ARG_STR_ACCOUNT,etAccount.getText().toString());
                intent.putExtra(ARG_STR_PSW, etPSW.getText().toString());
                startActivity(intent);
            }
        });
        btnDianwo=(Button)findViewById(R.id.btn_dianwo);
        btnDianwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,DianwoActivity.class);
                startActivity(intent);
            }
        });
        btnLt=(Button)findViewById(R.id.liaotian);
        btnLt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,DuihuaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTextView()
    {
        tvRegister = (TextView)findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvForgetPSW = (TextView)findViewById(R.id.tv_forget_psw);
        tvForgetPSW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
            }
        });
        tvCheckUpdate=(TextView)findViewById(R.id.tv_jieshi);
        tvCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(ACTION_NOTIFICATION);
                intent.putExtra(MyReceiver.NOTIFICATION_TITLE,"软件1402");
                intent.putExtra(MyReceiver.NOTIFICATION_CONTENT,"圣诞快乐");
                sendBroadcast(intent);
            }
        });
    }
    class  HttpURLTask extends AsyncTask {
        private  String account;
        private String password;

        public HttpURLTask(String account,String password){
            this.account=account;
            this.password=password;

        }
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent intent = new Intent(LoginActivity.this,IconTabsActivity.class);
            intent.putExtra(ARG_STR_ACCOUNT,etAccount.getText().toString());
            intent.putExtra(ARG_STR_PSW, etPSW.getText().toString());

            try{
               intent.putExtra(ARG_STR_RESPONSE,new String((byte[])o,"UTF-8").trim());
                startActivity(intent);
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
                URL url=new URL(path+account+"&pwd="+password);
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
