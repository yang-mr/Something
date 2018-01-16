package com.jack.root.something.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.jack.root.something.R;
import com.jack.root.something.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.logging.Logger;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.drawerlayout)
    private DrawerLayout mDrawerlayout;
    @ViewInject(R.id.navigationview)
    private NavigationView mNavigationView;
    @ViewInject(R.id.toolbar)
    public Toolbar mToolbar;
    @ViewInject(R.id.iv_switch)
    public ImageView mIvSwitch; // navigation switch

    private final static int TYPE_OPEN_DRAWER = 1;
    private final static int TYPE_CLOSE_DRAWER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(mToolbar);
        init();
    }

    @Override
    public void handleToolbar() {
        super.handleToolbar();

        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            MenuItem preItem = null;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (preItem != null) {
                    preItem.setChecked(false);
                }
                item.setChecked(true);
                Log.d("yw", "你点击了标题为" + item.getTitleCondensed() + "的菜单");
                Toast.makeText(MainActivity.this, "你点击了标题为" + item.getTitleCondensed() + "的菜单", Toast.LENGTH_LONG).show();
                mDrawerlayout.closeDrawers();
                preItem = item;   //保存一下上一次点击的item
                return false;
            }
        });


        // View headerView = mNavigationView.inflateHeaderView(R.layout.drawer_header);

        //获取navigationView的头部布局和里面的控件
        View headerView = mNavigationView.getHeaderView(0);
        ImageView avatar = headerView.findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yw", "你点击了头像");
                mDrawerlayout.closeDrawers();
                Toast.makeText(MainActivity.this, "你点击了头像", Toast.LENGTH_SHORT).show();
            }
        });

        mIvSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigationView.isShown();
                if (getDrawerStatue()) {
                    // close drawer
                    mDrawerlayout.closeDrawer(mNavigationView);
                    showAnimator(TYPE_CLOSE_DRAWER);
                } else {
                    // open drawer
                    mDrawerlayout.openDrawer(mNavigationView);
                    showAnimator(TYPE_OPEN_DRAWER);
                }
            }
        });

        mDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                com.orhanobut.logger.Logger.d(slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("yw", item + "");
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerlayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean getDrawerStatue() {
        return mDrawerlayout.isDrawerOpen(mNavigationView);
    }

    private void showAnimator(int type) {
        RotateAnimation animation;
        switch (type) {
            case TYPE_OPEN_DRAWER:
                animation = new RotateAnimation(0, 90);
                animation.setDuration(2000);
                animation.setFillAfter(true);
                mIvSwitch.startAnimation(animation);
                break;
            case TYPE_CLOSE_DRAWER:
                animation = new RotateAnimation(0, -90);
                animation.setDuration(2000);
                animation.setFillAfter(true);
                mIvSwitch.startAnimation(animation);
                break;
        }
    }
}
