package com.jack.root.something.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.jack.root.something.R;
import com.jack.root.something.ui.base.BaseFragment;

public class MomeFragment extends BaseFragment {
    private RecyclerView mRecyclerViewMemo;
    public MomeFragment() {
    }

    @Override
    protected void initView(View view) {
        mRecyclerViewMemo = view.findViewById(R.id.recyclerview_memo);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public int setView() {
        return R.layout.fragment_memo;
    }
}
