package sample.event.lisi.loginexample.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Administrator on 2015/12/25.
 */
public class BaseActivity extends ActionBarActivity {
    protected void  onResume(){
        super.onResume();
        startService(new Intent(this,MyService2.class));
    }
    protected void  onPause(){
        super.onPause();
        stopService(new Intent(this,MyService2.class));
    }
}
