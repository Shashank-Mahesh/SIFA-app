package org.sifa.application.sifa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Loader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        final TextView loadText = (TextView) findViewById(R.id.loading_text);
        if (loadText == null) {
            doIntent();
            finish();
        } else {
            Thread welcomeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long currTime = System.currentTimeMillis();

                    try {
                        while (System.currentTimeMillis() - currTime < 3000) {
                            loadText.setText("Loading . . .");
                            Thread.sleep(350);
                            loadText.setText("Loading . .");
                            Thread.sleep(350);
                            loadText.setText("Loading .");
                            Thread.sleep(350);
                            loadText.setText("Loading");
                            Thread.sleep(350);
                        }
                    } catch (Exception ignored) {
                    } finally {
                        doIntent();
                    }
                }
            });

            welcomeThread.start();
        }
    }

    private void doIntent() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
