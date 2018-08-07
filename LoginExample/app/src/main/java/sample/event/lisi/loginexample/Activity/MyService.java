package sample.event.lisi.loginexample.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;

import sample.event.lisi.loginexample.R;

public class MyService extends ActionBarActivity {
 Button btn_opens,btn_gbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_servoest);
        btn_opens=(Button)findViewById(R.id.btn_open);
        btn_gbs=(Button)findViewById(R.id.btn_gb);
    }
   private  void  init(){

   }

}
