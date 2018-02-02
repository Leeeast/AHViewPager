package com.iteast.ahviewpager.widget.viewpager;

import android.view.View;

/**
 * Created by lidongliang on 2018/2/2.
 */

public interface AHInterface<T extends View> {
    /**
     * 位置发生变化
     *
     * @param currentIndex
     */
    void onIndexChange(int currentIndex);

    void onLoading(T imageView, String url, int position);
}
