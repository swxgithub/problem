package com.example.youci;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {
    private TextView tvName;
    private SessionManager session;
    private BottomNavigationView bottomNaviView;
    private MenuItem menuitem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_homepage:
                    setContentView(R.layout.activity_main);
                    return true;
                case R.id.item_review:
                    setContentView(R.layout.review);
                    return true;
                case R.id.item_query:
                    setContentView(R.layout.query);
                    return true;
                case R.id.item_setting:
                    setContentView(R.layout.setting);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            //StrictMode.setThreadPolicy(policy);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navi_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        //StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
    }


}
