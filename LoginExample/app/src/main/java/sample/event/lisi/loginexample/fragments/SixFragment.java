package sample.event.lisi.loginexample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sample.event.lisi.loginexample.R;


public class SixFragment extends Fragment {
    public static final String BR_1 = "action.MainActivity.try";
    private Intent intent;
    private Button btnSend;
    public SixFragment() {
        // Required empty public constructor
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_six, container, false);
    }



}
