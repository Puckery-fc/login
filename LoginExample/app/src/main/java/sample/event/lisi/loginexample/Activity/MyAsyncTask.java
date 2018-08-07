package sample.event.lisi.loginexample.Activity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask {
    private Button btnExecute,btnCancel;
    private ProgressBar progressBar;
    private TextView tvMessage;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        btnExecute.setEnabled(true);
        btnCancel.setEnabled(false);
        tvMessage.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

    }

    public  MyAsyncTask(){
        this.btnCancel=btnCancel;
        this.btnExecute=btnExecute;
        this.progressBar=progressBar;
        this.tvMessage=tvMessage;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        btnExecute.setEnabled(true);
        btnCancel.setEnabled(false);
        tvMessage.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        String o=values[0].toString();
        progressBar.setProgress(Integer.parseInt(o));
        tvMessage.setText("loading...."+o+"%");
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            int count=0;
            while (count<10){
                Thread.sleep(1000);
                count++;
                publishProgress(count*10);
                Log.v("trace", "" + count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}