package com.jack.root.something.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jack.root.something.R;
import com.jack.root.something.db.model.MomeHeadModel;
import com.jack.root.something.db.model.MomeModel;

import java.util.List;

/**
 * Created by jack
 * On 18-2-6:上午11:08
 * Desc:
 */
public class MomeRecyclerAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = MomeRecyclerAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_MOME = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MomeRecyclerAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_mome_level0);
        addItemType(TYPE_MOME, R.layout.item_mome_content);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:

                final MomeHeadModel momeHeadModel = (MomeHeadModel)item;
                holder.setText(R.id.tv_type, momeHeadModel.type)
                        .setText(R.id.tv_time, momeHeadModel.date + "");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (momeHeadModel.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_MOME:
                final MomeModel momeModel = (MomeModel) item;
                holder.setText(R.id.tv_content, momeModel.content);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getAdapterPosition();
                        remove(pos);
                        return true;
                    }
                });
                break;
        }
    }
}
