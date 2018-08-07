package sample.event.lisi.loginexample.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import sample.event.lisi.loginexample.R;

public class RegisterActivity extends Activity {

    ImageView ivBack, ivInfo;
    Button btnRegister;
    EditText etAccount, etPassword, etVerifyPSW;
    public static String ARG_ACCOUNT = "ARG_ACCOUNT";
    public static String ARG_PASSWORD= "ARG_PASSWORD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        initImageView();
        initButton();
        initEditText();
    }

    private void initImageView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initButton() {
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAccount != null && !etAccount.getText().toString().equals("")) {
                    if (etPassword != null && etVerifyPSW != null &&
                            etPassword.getText().toString().equals(etVerifyPSW.getText().toString()))
                    {
                        MyAsyncTask registerTask=new MyAsyncTask();
                        registerTask.execute();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this," 帐号，密码不能为空，两次输入密码不相等",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initEditText() {
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_psw);
        etVerifyPSW = (EditText) findViewById(R.id.et_verify_psw);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
      private Dialog dialog;
     class  MyAsyncTask extends AsyncTask{
         @Override
         protected void onPreExecute(){
             super.onPreExecute();
             dialog=new AlertDialog.Builder(RegisterActivity.this)
                     .setIcon(R.drawable.info_icon)
                     .setMessage("请等候....注册中").show();
         }

         protected void onPostExecute(Object o) {
             super.onPostExecute(o);
             if(dialog!=null)
                 dialog.dismiss();
             Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
             intent.putExtra(ARG_ACCOUNT,etAccount.getText().toString());
             intent.putExtra(ARG_PASSWORD,etPassword.getText().toString());
             startActivity(intent);
         }

         @Override
         protected void onProgressUpdate(Object[] values) {
             super.onProgressUpdate(values);
         }

         @Override
         protected Object doInBackground(Object[] objects) {
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             return null;
         }
     }
}
