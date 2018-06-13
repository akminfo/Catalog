package com.fingertip.catalog;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.fingertip.catalog.adapter.DoubleDrawerView;

/**
 * Created by lenevo on 14-05-2018.
 */

public class SliderActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private DoubleDrawerView doubleDrawerView;
    private NavigationView mainNavigationView, settingsNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        doubleDrawerView = (DoubleDrawerView) findViewById(R.id.double_drawer_view);
        mainNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        settingsNavigationView = (NavigationView) findViewById(R.id.settings_navigation_view);

        mainNavigationView.setNavigationItemSelectedListener(this);
        settingsNavigationView.setNavigationItemSelectedListener(this);

        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_open_settings:
                doubleDrawerView.openInnerDrawer();
                break;

            case R.id.menu_close_settings:
                doubleDrawerView.closeInnerDrawer();
                break;

            // Additional cases as needed
            // This example simply Toasts the title for the extra sample items

            default:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
