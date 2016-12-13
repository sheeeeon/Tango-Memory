package com.icaynia.tangomemory.Tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.TestActivity;
import com.icaynia.tangomemory.TutorialActivity;

/**
 * Created by icaynia on 2016. 12. 8..
 */

public class TutorialFragment3 extends android.support.v4.app.Fragment {
    private View v;
    private TextView gobtn;

    public TutorialFragment3() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_tutorial3, container, false);
        initialize();
        return v;
    }

    public void initialize() {
        viewInitialize();
    }

    public void viewInitialize() {
        gobtn = (TextView) v.findViewById(R.id.action_go_btn);
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TestActivity.class);
                startActivity(intent);
                ((TutorialActivity)getContext()).finish();
            }
        });
    }
}
