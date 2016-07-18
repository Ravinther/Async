package com.example.ravi.async;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ProgressBar progressBar,progressBar2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar2);
        textView = (TextView) findViewById(R.id.textView1);
    }
    public void startProgress(View view) {
        // do something long
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    doFakeWork();
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("Updating");
                            progressBar.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    // Simulating something timeconsuming
    private void doFakeWork() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
