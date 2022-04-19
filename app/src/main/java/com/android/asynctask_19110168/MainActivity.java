package com.android.asynctask_19110168;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txtOutPut;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button1);
        txtOutPut = findViewById(R.id.output);
        progressBar = findViewById(R.id.progressBar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIncreaseNumber();
            }
        });
    }

    private void doIncreaseNumber(){
        MyAsync myAsync = new MyAsync();
        myAsync.execute();
    }
    private  class MyAsync extends AsyncTask<Void, Integer, String> {
        private int count;
        @Override
        protected void onPreExecute(){
            count = 0;
        }

        @Override
        protected String doInBackground(Void... params) {
            while (count < 101){
                if(count==100){
                    return null;
                }
                count += 10;
                publishProgress(count);
                try {
                    Thread.sleep(6000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate (Integer... result) {
            if(result[0] < 100){
                txtOutPut.setText("Working...");
                progressBar.setProgress(result[0]);
            }else{
                progressBar.setProgress(result[0]);
                txtOutPut.setText("Completed");
            }
        }
    }
}