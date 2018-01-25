package com.jack.root.something.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jack
 * On 18-1-25:上午9:47
 * Desc:
 */

public class MainViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
