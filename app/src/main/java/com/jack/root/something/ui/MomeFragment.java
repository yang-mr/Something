package com.jack.root.something.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jack.root.something.R;
import com.jack.root.something.ui.base.BaseFragment;

public class MomeFragment extends BaseFragment {

    public MomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mome, null);
        return view;
    }
}
