package com.iteast.ahviewpager;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.iteast.ahviewpager.widget.viewpager.AHInterface;
import com.iteast.ahviewpager.widget.viewpager.AHViewPager;
import com.iteast.ahviewpager.widget.viewpager.Utils;

public class ScrollingActivity extends AppCompatActivity implements AHInterface<SimpleDraweeView> {
    private String[] datas = new String[]{
            "http://www.quanjing.com/image/2017index/lx1.png",
            "http://imgsrc.baidu.com/imgad/pic/item/241f95cad1c8a7860ea6962d6d09c93d70cf5001.jpg",
            "http://pic.qiantucdn.com/58pic/16/69/41/02758PICS2Q_1024.jpg",
            "http://imgsrc.baidu.com/imgad/pic/item/a50f4bfbfbedab6440d4dfe5fd36afc379311e74.jpg",
            "http://img.tuku.cn/file_big/201503/d8905515d1c046aeba51025f0ea842f0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1395710768,4003046922&fm=214&gp=0.jpg",
            "http://www.pp3.cn/uploads/201412/2014123114.jpg"
    };
    private AHViewPager mu5ViewPager;
    private TextView mTvPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mu5ViewPager = findViewById(R.id.viewpager);
        mu5ViewPager.setData(datas, this);//datas支持绑定类型String[] 或者 List<String>
        mTvPageIndex = findViewById(R.id.tv_page_index);
    }


    @Override
    public void onIndexChange(int currentIndex) {
        String index = currentIndex + 1 + "/" + mu5ViewPager.getAdapter().getCount();
        mTvPageIndex.setText(index);
    }

    @Override
    public void onLoading(SimpleDraweeView imageView, String url, int position) {
        showImagePic(imageView, url, position);
    }

    /**
     * 控制图片宽高
     *
     * @param sdv
     * @param imagePath
     */
    public void showImagePic(final SimpleDraweeView sdv, final String imagePath, final int position) {
        final int maxWidth = Utils.getDisplayWidth(this);
        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        final ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int width = imageInfo.getWidth();
                int height = imageInfo.getHeight();
                layoutParams.width = maxWidth;
                layoutParams.height = (int) ((float) (maxWidth * height) / (float) width);
                sdv.setLayoutParams(layoutParams);
                mu5ViewPager.setSourceHeights(layoutParams.height, position);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(imagePath).build();
        sdv.setController(controller);
    }
}
