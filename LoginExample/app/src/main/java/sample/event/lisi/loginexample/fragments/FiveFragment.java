package sample.event.lisi.loginexample.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import sample.event.lisi.loginexample.R;


public class FiveFragment extends Fragment {
    private  Button btn1,btn2;
    private ProgressBar progressBar;
    public FiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         System.out.println("trace main Thread:" + Thread.currentThread().getId());
        btn1=(Button)getActivity().findViewById(R.id.btn_execute);
        progressBar=(ProgressBar)getActivity().findViewById(R.id.progressbar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             progressBar.setVisibility(View.VISIBLE);
             handler.post(r);
            }

        });

    }
    Handler handler= new Handler(){
       public void  handleMessage(Message msg){
           super.handleMessage(msg);
           Log.i("MainActivity","handle...");
           progressBar.setProgress(msg.arg1);
           handler.post(r);
       }
    };
    Runnable r= new Runnable() {

        int number=0;
        public void run() {
            Log.i("MainActivity","begin thread...");
            number=number+10;
           Message msg=handler.obtainMessage();
            msg.arg1=number;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){e.printStackTrace();}
            if(number<100)handler.sendMessage(msg);
            if(number==100){
                handler.removeCallbacks(r);
                progressBar.setVisibility(View.GONE);
            }
        }

    };
}
