package sample.event.lisi.loginexample.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Administrator on 2015/12/21.
 */
public class MyService2 extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public  void  onCreate(){
        super.onCreate();
        System.out.print("MyService2 onCreate");
    }
    public  int onStarCommand(Intent intent,int flags,int startId){
        System.out.println("MyService2 onStartCommand");
        return  super.onStartCommand(intent,flags,startId);
    }
    public  void  onDestroy(){
        super.onDestroy();
        System.out.println("MyService2 onDestroy");
    }
}
