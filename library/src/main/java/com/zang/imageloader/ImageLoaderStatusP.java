package com.zang.imageloader;

import android.graphics.Bitmap;
import android.view.View;

/**
 * 图片加载状态回调
 */
public interface ImageLoaderStatusP {
    /**
     * 开始加载
     */
    void onStart();

    /**
     * 加载进度
     *
     * @param progress
     */
    void onProgress(int progress);

    /**
     * 加载成功回调
     *
     * @param view
     * @param bitmap
     */
    void onSuccess(View view, Bitmap bitmap);
}
