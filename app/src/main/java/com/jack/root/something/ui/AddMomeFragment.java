package com.jack.root.something.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jack.root.something.R;
import com.jack.root.something.db.DbManager;
import com.jack.root.something.db.model.MomeModel;
import com.jack.root.something.ui.base.BaseFragment;

import java.util.Date;

/**
 * Created by jack
 * On 18-2-5:下午5:07
 * Desc:
 */
public class AddMomeFragment extends BaseFragment {
    private EditText mEtTitle;
    private EditText mEtContent;
    private Button mBtAdd;
    @Override
    protected void initView(View view) {
        mEtTitle = view.findViewById(R.id.et_title);
        mEtContent = view.findViewById(R.id.et_content);
        mBtAdd = view.findViewById(R.id.bt_ok);
        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert data
                String title = mEtTitle.getText().toString().trim();
                String content = mEtContent.getText().toString().trim();

                MomeModel momeModel = new MomeModel(title, content, new Date(), 1);
                DbManager.getInstance().addMome(momeModel);
            }
        });
    }

    @Override
    public int setView() {
        return R.layout.add_mome_layout;
    }
}
