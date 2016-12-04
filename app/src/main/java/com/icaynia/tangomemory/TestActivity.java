package com.icaynia.tangomemory;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.icaynia.tangomemory.R.id.drawerLayout;

/**
 * Created by icaynia on 2016. 12. 4..
 */

public class TestActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence mActionBarTitle;
    private CharSequence mMenuTitle;

    private String[] navItems = {"fragment", "fragment2", "fragment3"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // toolbar setting
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.setStatusBarBackgroundColor(0xEEEEEE);
        //listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));

        setActionBar();
        setDrawer();

        if (savedInstanceState == null) {

        }
    }

    private void setActionBar() {
        mMenuTitle = mActionBarTitle = getTitle();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    private void setDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, R.string.app_name) {
            @ Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mActionBarTitle);
                invalidateOptionsMenu();
            }

            @ Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mMenuTitle);
                invalidateOptionsMenu();
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);

        mDrawerLayout.setDrawerListener(drawerToggle);
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    }

}
