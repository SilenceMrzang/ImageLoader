package com.zang.imageloader.factory;

import android.content.Context;
import android.support.annotation.StringDef;
import android.text.TextUtils;

import com.zang.imageloader.GlideImageLoader;
import com.zang.imageloader.ImageLoaderP;
import com.zang.imageloader.PicassoImageLoader;
import com.zang.imageloader.UlImageloader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * imageLoader
 * 工厂类
 * 统一调用
 * 子类实现加载图片方法
 */
public class BaseLoaderFactory {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({LoaderFactory.GLID_IMAGE_LOADER, LoaderFactory.PICASSO_IMAGE_LOADER, LoaderFactory.UI_IMAGE_LOADER})
    public @interface LoaderFactory {
        String UI_IMAGE_LOADER = "com.nostra13.universalimageloader.core.ImageLoader";
        String GLID_IMAGE_LOADER = "com.bumptech.glide.Glide";
        String PICASSO_IMAGE_LOADER = "com.squareup.picasso.Picasso";
    }

    public static ImageLoaderP createImageLoader(Context context, @LoaderFactory String imageLoaderFactory) {
        ImageLoaderP imageLoaderP = null;
        if (context == null || TextUtils.isEmpty(imageLoaderFactory))
            throw new NullPointerException("ImageLoader 工厂类参数有误");
        switch (imageLoaderFactory) {
            case LoaderFactory.UI_IMAGE_LOADER:
                return new UlImageloader(context.getApplicationContext());
            case LoaderFactory.GLID_IMAGE_LOADER:
                return new GlideImageLoader(context.getApplicationContext());
            case LoaderFactory.PICASSO_IMAGE_LOADER:
                return new PicassoImageLoader(context.getApplicationContext());
        }
        return imageLoaderP;
    }
}
