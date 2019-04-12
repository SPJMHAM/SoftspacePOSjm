package com.example.softspaceposjm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Akshay Raj on 17-04-2017.
 * akshay@snowcorp.org
 * www.snowcorp.org
 */
public class FrgmentTwo extends Fragment {
    public FrgmentTwo() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frgment_two, container, false);
    }
}