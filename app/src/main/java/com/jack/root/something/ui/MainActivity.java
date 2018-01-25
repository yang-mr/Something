package com.jack.root.something.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.root.something.R;
import com.jack.root.something.adapter.MainViewPagerAdapter;
import com.jack.root.something.ui.base.BaseActivity;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.drawerlayout)
    private DrawerLayout mDrawerlayout;
    @ViewInject(R.id.navigationview)
    private NavigationView mNavigationView;
    @ViewInject(R.id.toolbar)
    public Toolbar mToolbar;
    @ViewInject(R.id.iv_switch)
    public TextView mIvSwitch; // navigation switch

    @ViewInject(R.id.viewpager_main)
    private ViewPager mViewPager;

    private final static int TYPE_OPEN_DRAWER = 1;
    private final static int TYPE_CLOSE_DRAWER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        init();
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
                if (!mDrawerlayout.isDrawerOpen(mNavigationView)) {
                    // close drawer
                    showAnimator(TYPE_OPEN_DRAWER, new OnListenerByAnimationEnd() {
                        @Override
                        public void onAnimationEnd() {
                            mDrawerlayout.openDrawer(mNavigationView);
                        }
                    });
                }
            }
        });

        mDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Logger.d(slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                showAnimator(TYPE_CLOSE_DRAWER);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        List<Fragment> list = testViewpager();
        mViewPager.setAdapter(new MainViewPagerAdapter(this.getSupportFragmentManager(), list));  // init ViewPager adapter

        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                // await test
            }
        });
    }

    private List<Fragment> testViewpager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new MomeFragment());
        list.add(new MomeFragment());
        list.add(new MomeFragment());
        return list;
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

    private void showAnimator(int type) {
        showAnimator(type, null);
    }

    private void showAnimator(int type, final OnListenerByAnimationEnd onListenerByAnimationEnd) {
        RotateAnimation animation;
        switch (type) {
            case TYPE_OPEN_DRAWER:
                animation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (onListenerByAnimationEnd != null) {
                            onListenerByAnimationEnd.onAnimationEnd();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mIvSwitch.startAnimation(animation);
                break;
            case TYPE_CLOSE_DRAWER:
                animation = new RotateAnimation(90, 0, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                animation.setDuration(1000);
                animation.setFillAfter(true);
                mIvSwitch.startAnimation(animation);
                break;
        }
    }

    private interface OnListenerByAnimationEnd {
       void onAnimationEnd();
    }
}
