package com.zang.imageloader;

import android.content.Context;

import com.zang.imageloader.factory.BaseLoaderFactory;

public class EduImageLoader {

    private EduImageLoader() {
    }

    private static ImageLoaderP imageLoaderP;

    public static ImageLoaderP getImageLoader(Context context) {

        synchronized (EduImageLoader.class) {
            if (imageLoaderP == null) {
                if (isClassExists("com.bumptech.glide.Glide")) {
                    imageLoaderP = BaseLoaderFactory.createImageLoader(context, BaseLoaderFactory.LoaderFactory.GLID_IMAGE_LOADER);
                } else if (isClassExists("com.squareup.picasso.Picasso")) {
                    imageLoaderP = BaseLoaderFactory.createImageLoader(context, BaseLoaderFactory.LoaderFactory.PICASSO_IMAGE_LOADER);
                } else if (isClassExists("com.nostra13.universalimageloader.core.ImageLoader")) {
                    imageLoaderP = BaseLoaderFactory.createImageLoader(context, BaseLoaderFactory.LoaderFactory.UI_IMAGE_LOADER);
                } else {
                    throw new RuntimeException("必须在你的build.gradle文件中配置「Glide、Picasso、universal-image-loader」中的某一个图片加载库的依赖");
                }
            }
        }
        return imageLoaderP;
    }

    private static final boolean isClassExists(String classFullName) {
        try {
            Class.forName(classFullName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
