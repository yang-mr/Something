package com.jack.root.something.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jack.root.something.R;
import com.jack.root.something.adapter.MomeRecyclerAdapter;
import com.jack.root.something.db.DbManager;
import com.jack.root.something.db.model.MomeHeadModel;
import com.jack.root.something.db.model.MomeModel;
import com.jack.root.something.ui.base.BaseFragment;
import com.jack.root.something.utils.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MomeFragment extends BaseFragment {
    private RecyclerView mRecyclerViewMemo;
    private MomeRecyclerAdapter mMomeRecyclerAdapter;

    public MomeFragment() {
    }

    @Override
    protected void initView(View view) {
        mRecyclerViewMemo = view.findViewById(R.id.recyclerview_memo);
    }

    @Override
    protected void initData() {
        super.initData();

        mRecyclerViewMemo.setLayoutManager(new GridLayoutManager(mContext, 4));

        List<MomeModel> momeList = DbManager.getInstance().getAllMome();
        boolean isContainer = false;
        List<MultiItemEntity> momeHeadModels = new ArrayList<>();
        for (MomeModel model : momeList) {
            String date = Util.date2Str(model.date);

            for (MultiItemEntity entity : momeHeadModels) {
                MomeHeadModel momeHeadModel =(MomeHeadModel)entity;
                String date2 = momeHeadModel.date;
                if (date2.equals(date)) {
                    isContainer = true;
                    momeHeadModel.addSubItem(model);
                    break;
                }
            }
            if (!isContainer) {
                MomeHeadModel momeHeadModel = new MomeHeadModel(0, date);
                momeHeadModels.add(momeHeadModel);
                momeHeadModel.addSubItem(model);
            }
        }

        mMomeRecyclerAdapter = new MomeRecyclerAdapter(momeHeadModels);
        mRecyclerViewMemo.setAdapter(mMomeRecyclerAdapter);
    }

    @Override
    public int setView() {
        return R.layout.fragment_memo;
    }
}
