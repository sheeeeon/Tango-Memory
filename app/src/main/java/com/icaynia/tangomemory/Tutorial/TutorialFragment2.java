package com.icaynia.tangomemory.Tutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.icaynia.tangomemory.R;
import com.icaynia.tangomemory.View.Card;
import com.icaynia.tangomemory.View.GameActivityView;
import com.icaynia.tangomemory.View.TodaysActivityView;
import com.icaynia.tangomemory.View.YourTangoActivityView;

/**
 * Created by icaynia on 2016. 12. 8..
 */

public class TutorialFragment2 extends android.support.v4.app.Fragment {
    private LinearLayout v;
    private Card card1;
    private Card card2;
    private Card card3;

    public TutorialFragment2() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = (LinearLayout) inflater.inflate(R.layout.fragment_tutorial2, container, false);
        initialize();
        return v;
    }

    public void initialize() {
        viewInitialize();

        card1();
        card2();
        card3();
    }

    public void viewInitialize() {

        card1 = (Card) v.findViewById(R.id.card1);
        card2 = (Card) v.findViewById(R.id.card2);
        card3 = (Card) v.findViewById(R.id.card3);
    }

    private void card1() {
        card1.setTitle("Today's Activity");
        TodaysActivityView tav = new TodaysActivityView(getContext());
        tav.setSolvedcountValue("151");

        tav.setTodayaddcountValue("43");

        card1.setContent(tav.getView());
    }

    private void card2() {
        card2.setTitle("Your Word Activity");
        YourTangoActivityView tav = new YourTangoActivityView(getContext());
        tav.setWordquantityValue(
                "583"
        );

        card2.setContent(tav.getView());
    }

    private void card3() {
        card3.setTitle("Game Activity");
        GameActivityView tav = new GameActivityView(getContext());
        tav.setAllcountValue("1155");
        tav.setKanjitohiraganaValue("1155");

        card3.setContent(tav.getView());
    }
}
