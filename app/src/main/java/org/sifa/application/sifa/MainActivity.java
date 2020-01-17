package org.sifa.application.sifa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "shank";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OneSignal.startInit(this).setNotificationOpenedHandler(new NotificationOpenedHandler()).init();

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tb = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        vpAdapter.addFragment(new ConcertsList(), "Concerts");
        vpAdapter.addFragment(new Feedback(), "Feedback");
        vpAdapter.addFragment(new Other(), "Other");

        viewPager.setAdapter(vpAdapter);

        tb.setupWithViewPager(viewPager);

    }

    // This fires when a notification is opened by tapping on it or one is received while the app is running.
    private class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(String message, JSONObject additionalData, boolean isActive) {
            try {
                if (additionalData != null) {
                    if (additionalData.has("actionSelected"))
                        Log.d(TAG, "OneSignal notification button with id " + additionalData.getString("actionSelected") + " pressed");

                    Log.d(TAG, "Full additionalData:\n" + additionalData.toString());
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

}
