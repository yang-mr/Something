package com.jack.root.something.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.jack.root.something.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class BaseActivity extends AppCompatActivity {


    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
//                .statusBarColor(R.color.colorPrimary)
//                .init();

       // setSupportActionBar(mToolbar);
        handleToolbar();
    }

   public void handleToolbar(){
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
