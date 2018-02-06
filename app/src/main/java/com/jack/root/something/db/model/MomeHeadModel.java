package com.jack.root.something.db.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jack.root.something.adapter.MomeRecyclerAdapter;

import java.util.Date;

/**
 * Created by jack
 * On 18-2-6:下午2:33
 * Desc:
 */

public class MomeHeadModel extends AbstractExpandableItem<MomeModel> implements MultiItemEntity {
    public int type;
    public String date;

    public MomeHeadModel(int type, String date) {
        this.type = type;
        this.date = date;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return MomeRecyclerAdapter.TYPE_LEVEL_0;
    }
}
