package sample.event.lisi.loginexample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import sample.event.lisi.loginexample.Activity.MyAsyncTask;
import sample.event.lisi.loginexample.R;


public class FourFragment extends Fragment {
    private Button btnExecute,btnCancel;
    private ProgressBar progressBar;
    private TextView tvMessage;
    private MyAsyncTask task;
    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four, container, false);

    }

    private void init() {
        tvMessage=(TextView)getActivity().findViewById(R.id.tv_message);
        btnExecute=(Button)getActivity().findViewById(R.id.btn_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task=new MyAsyncTask();
                task.execute();
            }

        });
        btnCancel=(Button)getActivity().findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(task!=null)
                    task.cancel(true);
            }
        });
        progressBar=(ProgressBar)getActivity().findViewById(R.id.progressbar);
    }
}
