package com.icaynia.tangomemory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.icaynia.tangomemory.wordtest.WordToHiragana;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    public wordManager mWordManager;
    public logManager mLogManager;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TextView addwordtv;
    private TextView searchtv;
    private TextView searchcanceltv;
    private View dialogV;

    private int wordRows;
    private int wordRowsToday;

    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd" , Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWordManager = new wordManager(this);
        mLogManager = new logManager(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        wordRows = mWordManager.getWordRows();
        wordRowsToday = mWordManager.getWordRowsToday();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    onAddwordbutton(true);
                    onSearchButton(false);
                } else {
                    onAddwordbutton(false);
                    onSearchButton(false);
                }
                wordRows = mWordManager.getWordRows();
                wordRowsToday = mWordManager.getWordRowsToday();
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        addwordtv = (TextView) findViewById(R.id.addwordView);
        addwordtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddwordDialog();

            }
        });

        searchtv = (TextView) findViewById(R.id.searchTextView);
        searchtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddwordbutton(false);
                onSearchCancelButton(true);
                onSearchButton(false);
            }
        });

        searchcanceltv = (TextView) findViewById(R.id.search_cancel);
        searchcanceltv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddwordbutton(true);
                onSearchCancelButton(false);
                onSearchButton(true);
            }
        });

        onAddwordbutton(false);
        onSearchButton(false);

        //Navigation Drawer


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(((MainActivity)getContext()).getFragmentId(getArguments().getInt(ARG_SECTION_NUMBER)-1), container, false);

            if (getArguments().getInt(ARG_SECTION_NUMBER)-1 == 0) {
                ((MainActivity)getContext()).onFragment1(rootView);

            } else if (getArguments().getInt(ARG_SECTION_NUMBER)-1 == 1) {
                ((MainActivity)getContext()).onFragment2(rootView);

            } else if (getArguments().getInt(ARG_SECTION_NUMBER)-1 == 2){
                ((MainActivity)getContext()).onFragment3(rootView);

            }
            return rootView;
        }




    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "대쉬보드";
                case 1:
                    return "단어";
                case 2:
                    return "단어 게임";
                case 3:
                    return "설정";
            }
            return null;
        }


    }

    public int getFragmentId(int index) {
        switch (index) {
            case 0:
                return R.layout.fragment_home;
            case 1:
                return R.layout.fragment_2;
            case 2:
                return R.layout.fragment_3;
            case 3:
                return R.layout.fragment_4;
        }
        return 0;
    }

    public void onFragment1(View v) {
        TextView wordcountTv = (TextView) v.findViewById(R.id.wordcountTv);
        wordcountTv.setText(wordRows+"");

        TextView todayWordcountTv = (TextView) v.findViewById(R.id.todayWordcountTv);
        todayWordcountTv.setText(wordRowsToday+"");

        TextView yesterdayWordcountTv = (TextView) v.findViewById(R.id.yesterdayWordcountTv);
        yesterdayWordcountTv.setText(mWordManager.getWordRowsYesterday()+"");

        TextView todayCountTv = (TextView) v.findViewById(R.id.TodayWordCount);
        String now = transFormat.format(new Date()).toString();
        todayCountTv.setText(mLogManager.getCount(now)+"");

        TextView allCountTv = (TextView) v.findViewById(R.id.AllWordCount);
        allCountTv.setText(mLogManager.getCount()+"");
    }

    public void onFragment2(View v) {
        mWordManager.connectAdapter(v);
    }

    public void onFragment3(View v) {
        TextView kanjiToHiraganaMenu = (TextView) v.findViewById(R.id.kanjiToHiraganaMenu);
        kanjiToHiraganaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWordToHiragana();
            }
        });
    }

    public void onWordToHiragana() {
        Intent intent = new Intent(this, WordToHiragana.class);
        startActivity(intent);
    }

    public void SearchEditTextView(boolean _b) {
        if (_b) {
            onAddwordbutton(false);

        }
        else if (!_b) {
            onAddwordbutton(true);

        }

    }

    public void onSearchButton(boolean _b) {
        if (_b) {
            searchtv.setVisibility(View.VISIBLE);
        }
        else if (!_b) {
            searchtv.setVisibility(View.GONE);

        }
    }

    public void onSearchCancelButton(boolean _b) {
        if (_b) {
            searchcanceltv.setVisibility(View.VISIBLE);
        }
        else if (!_b) {
            searchcanceltv.setVisibility(View.GONE);

        }
    }

    public void onAddwordbutton(boolean _b) {
        if (_b) {
            addwordtv.setVisibility(View.VISIBLE);
        }
        else if (!_b) {
            addwordtv.setVisibility(View.GONE);
        }
    }

    public void onAddwordDialog() {
        dialogV = getLayoutInflater().inflate(R.layout.dialog_addword, null);
        final AlertDialog.Builder   builder     = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        final EditText et_word = (EditText) dialogV.findViewById(R.id.et_word);
        final TextView warning = (TextView) dialogV.findViewById(R.id.warning);
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
                    mWordManager.listRefrash();
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

    @Override
    public void onPause() {
        super.onPause();
    }



}


