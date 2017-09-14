package com.example.admin.login;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class tabactivity extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabactivity);

        Resources ressources = getResources();
        TabHost tabHost = getTabHost();

        // Android tab
        Intent intentAndroid = new Intent().setClass(this, AndroidActivity.class);
        TabSpec tabSpecAndroid = tabHost
                .newTabSpec("Android")
                .setIndicator("HOME")
                .setContent(intentAndroid);

        // Apple tab
        Intent intentApple = new Intent().setClass(this, AppleActivity.class);
        TabSpec tabSpecApple = tabHost
                .newTabSpec("Apple")
                .setIndicator("CASE")
                .setContent(intentApple);

        // Windows tab
        Intent intentWindows = new Intent().setClass(this, WindowsActivity.class);
        TabSpec tabSpecWindows = tabHost
                .newTabSpec("Windows")
                .setIndicator("third")
                .setContent(intentWindows);

        // Blackberry tab
        Intent intentBerry = new Intent().setClass(this, BlackBerryActivity.class);
        TabSpec tabSpecBerry = tabHost
                .newTabSpec("Berry")
                .setIndicator("four")
                .setContent(intentBerry);

        // add all tabs
        tabHost.addTab(tabSpecAndroid);
        tabHost.addTab(tabSpecApple);
        tabHost.addTab(tabSpecWindows);
        tabHost.addTab(tabSpecBerry);

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(0);
    }

}