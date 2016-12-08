package com.icaynia.tangomemory;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.icaynia.tangomemory.Data.wordManager;
import com.icaynia.tangomemory.Fragments.GameFragment;
import com.icaynia.tangomemory.Fragments.HomeFragment;
import com.icaynia.tangomemory.Fragments.LoginFragment;
import com.icaynia.tangomemory.Fragments.SettingFragment;
import com.icaynia.tangomemory.Fragments.WordFragment;

import java.util.List;

/**
 * Created by icaynia on 2016. 12. 4..
 */

public class TestActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private ListView listView;

    private CharSequence mActionBarTitle;
    private CharSequence mMenuTitle;

    private Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    private boolean actionButtonVisible = false;

    private String[] navItems = {"fragment", "fragment2", "fragment3"};

    private View dialogV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // toolbar setting
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.setStatusBarBackgroundColor(0xEEEEEE);

        setActionBar();
        setDrawer();
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        if (savedInstanceState == null) {

        }

        getSupportFragmentManager().beginTransaction().add(R.id.content, new HomeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        MenuItem m = menu.findItem(R.id.action_add);
        if (!actionButtonVisible) m.setVisible(false);
        else m.setVisible(true);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                onAddwordDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setActionBar() {
        mMenuTitle = mActionBarTitle = getTitle();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.action_settings, R.string.app_name){
            public void onDrawerClosed(View view)
            {

                getSupportActionBar().setTitle(mMenuTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
        }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Settings");
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };

        final Drawer drawer = (Drawer) findViewById(R.id.drawer);
        listView = drawer.listView;

        drawer.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    actionButtonVisible = true;
                } else {
                    actionButtonVisible = false;
                }
                switch (position) {
                    case 0: // Home
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new HomeFragment()).commit();
                        break;
                    case 1: // Word
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new WordFragment()).commit();
                        break;
                    case 2: // Game
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new GameFragment()).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new SettingFragment()).commit();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new LoginFragment()).commit();
                        break;

                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    }
                }, 150);
            }

        });


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

    public void onAddwordDialog() {
        dialogV = getLayoutInflater().inflate(R.layout.dialog_addword, null);
        final AlertDialog.Builder   builder     = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        final EditText et_word = (EditText) dialogV.findViewById(R.id.et_word);
        final TextView warning = (TextView) dialogV.findViewById(R.id.warning);

        final wordManager mWordManager = new wordManager(getApplicationContext());

        et_word.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (mWordManager.isAlreadyUsed(et_word.getText().toString())) {
                        warning.setText("This word is already used.");
                        Log.e("onFocusChange","used");
                    } else {
                        warning.setText("");
                    }
                }
            }
        });
        builder.setTitle("단어 추가하기");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText et_hiragana = (EditText) dialogV.findViewById(R.id.et_hiragana);
                EditText et_korean = (EditText) dialogV.findViewById(R.id.et_korean);
                if (!et_word.getText().toString().isEmpty() && !mWordManager.isAlreadyUsed(et_word.getText().toString())) {
                    mWordManager.addWord(et_word.getText().toString(), et_hiragana.getText().toString(), et_korean.getText().toString());
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new WordFragment()).commit();
                    dialog.dismiss();
                }


            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //((MainActivity)getContext()).makeToast("Scene 작성을 취소하였습니다.");
                dialog.dismiss();
            }
        });

        builder.setView(dialogV);
        //데이터 관련


        final AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();    // 알림창 띄우기

    }


}
