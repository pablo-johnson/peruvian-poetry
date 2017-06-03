package com.johnson.pablo.poesiaperuana.presentation.main;

import android.os.Bundle;

import com.johnson.pablo.poesiaperuana.R;
import com.johnson.pablo.poesiaperuana.presentation.common.PoetsActivity;
import com.johnson.pablo.poesiaperuana.presentation.poetsList.PoetsListFragment;

public class MainActivity extends PoetsActivity implements PoetsListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            PoetsListFragment poetsListFragment = PoetsListFragment.newInstance();
            replaceFragment(poetsListFragment, false);
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }
}
