package com.zang.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Glide 加载库
 */
public class GlideImageLoader extends BaseImageLoader {
    private Context mContext;

    public GlideImageLoader(Context context) {
        mContext = context.getApplicationContext();
        init();
    }

    private void init() {
    }

    @Override
    public void disPlay(String url, ImageView view) {
        //重写 父类方法
    }
}
