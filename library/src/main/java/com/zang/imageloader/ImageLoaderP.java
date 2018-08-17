package com.zang.imageloader;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * ImageLoader 接口
 * 由实现类 实现 相关方法
 */
public interface ImageLoaderP {

    /**
     * 加载图片
     *
     * @param url
     * @param view
     */
    void disPlay(String url, ImageView view);

    /**
     * 加载图片
     *
     * @param url
     * @param view
     * @param fillet 圆角弧度
     */
    void disPlay(String url, ImageView view, int fillet);

    /**
     * 加载图片
     * @param url
     * @param view
     * @param options
     */
    void disPlay(String url, ImageView view, DisplayImageOptions options);

    /**
     * 加载图片
     *
     * @param url
     * @param view
     * @param fillet
     * @param statusP
     */
    void disPlay(String url, ImageView view, int fillet, ImageLoaderStatusP statusP);

    /**
     * 加载图片
     *
     * @param url
     * @param view
     */
    void disPlay(String url, ImageView view, ImageLoaderStatusP statusP);
}
