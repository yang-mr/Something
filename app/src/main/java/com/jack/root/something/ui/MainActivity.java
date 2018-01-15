package com.jack.root.something.ui;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.jack.root.something.R;
import com.jack.root.something.ui.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.drawerlayout)
    private DrawerLayout mDrawerlayout;
    @ViewInject(R.id.navigationview)
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   mNavigationView.setItemIconTintList(null); //icon显示自身的颜色

        init();
    }

    @Override
    public void handleToolbar() {
        super.handleToolbar();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("记事本");
        //actionBar.setNavigationIcon(R.mipmap.ic_launcher);
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


        View headerView =
                mNavigationView.inflateHeaderView(R.layout.drawer_header);

        //获取navigationView的头部布局和里面的控件
       // View headerView = mNavigationView.getHeaderView(0);
        ImageView avatar = headerView.findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yw", "你点击了头像");
                mDrawerlayout.closeDrawers();
                Toast.makeText(MainActivity.this, "你点击了头像", Toast.LENGTH_SHORT).show();
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
}
