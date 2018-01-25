package com.jack.root.something.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jackyang on 18-1-15.
 *
 * @DESCRIPTION --------------------
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setView(), null);
        initView(view);
        initData();
        return view;
    }

    protected void initData() {

    }

    protected abstract void initView(View view);

    public abstract int setView();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
