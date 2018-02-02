package com.iteast.ahviewpager.widget.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.iteast.ahviewpager.R;

import java.util.List;

/**
 * Created by lidongliang on 2018/2/2.
 * 主要用于实现imageView的回掉加载
 */

public class AHPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> urls;
    private AHInterface mAHInterface;

    public Object toString(String s) {
        return this == null ? "null" : toString();
    }

    public AHPagerAdapter(Context mContext, List<String> urls) {
        this.mContext = mContext;
        this.urls = urls;
    }

    public AHPagerAdapter(Context mContext, List<String> urls, AHInterface ahInterface) {
        this.mContext = mContext;
        this.urls = urls;
        this.mAHInterface = ahInterface;
    }

    public void setMu5Interface(AHInterface ahInterface) {
        this.mAHInterface = ahInterface;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls == null ? 0 : urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View)
            container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView = new ImageView(mContext);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) LayoutInflater.from(container.getContext()).inflate(R.layout.simple_drawee_image, null);
        if (mAHInterface != null && urls != null) {
            mAHInterface.onLoading(simpleDraweeView, urls.get(position), position);
        }
        container.addView(simpleDraweeView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return simpleDraweeView;
    }
}
