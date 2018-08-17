package com.zang.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class UlImageloader extends BaseImageLoader {
    private Context mContext;

    public UlImageloader(Context context) {
        this.mContext = context;
        // init
        init();
    }

    /**
     * 初始化配置 Imageloader
     */
    private void init() {

        // Image loadere
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.image_load)
                .showImageForEmptyUri(R.drawable.image_empty)
                .showImageOnFail(R.drawable.image_error)
                .delayBeforeLoading(500) // 延迟载入 500 毫秒
                .cacheInMemory(false) // 内存缓存
                .cacheOnDisk(true) // 磁盘缓存
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, "imageLoader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .memoryCacheExtraOptions(1024, 768) //default = device screen dimensions 缓存最大图片大小
                .diskCacheExtraOptions(1080, 768, null) //闪存图片大小
                .threadPoolSize(3) //最大线程数
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 线程优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default 线程处理队列 先进先出
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //LruMemory
                .memoryCacheSize(2 * 1024 * 1024) // 缓存
                .memoryCacheSizePercentage(13)    // default 缓存比例？
                .diskCacheSize(50 * 1024 * 1024) // 闪存缓存大小
                .diskCacheFileCount(100) // 闪存缓存图片文件数量
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default 磁盘缓存位置
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default 文件名
                .imageDownloader(new BaseImageDownloader(mContext)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .writeDebugLogs()
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(config);
    }

    @Override
    public void disPlay(String url, ImageView view) {
        this.disPlay(url, view, 0);
    }

    @Override
    public void disPlay(String url, ImageView view, int fillet) {
        this.disPlay(url, view, fillet, null);
    }

    @Override
    public void disPlay(String url, ImageView view, int fillet, final ImageLoaderStatusP statusP) {
        if (TextUtils.isEmpty(url)) return;
        if (view == null) return;

        DisplayImageOptions options = null;

        if (fillet != 0) {
            options = new DisplayImageOptions.Builder()
                    .displayer(new RoundedBitmapDisplayer(fillet))//是否设置为圆角，弧度为多少
                    .build();
        }

        if (statusP != null) {
            ImageLoader.getInstance().displayImage(url, view, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                    statusP.onStart();
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason reason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    statusP.onSuccess(view, bitmap);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
        } else {
            ImageLoader.getInstance().displayImage(url, view, options);
        }
    }

    @Override
    public void disPlay(String url, ImageView view, ImageLoaderStatusP statusP) {
        this.disPlay(url, view, 0, statusP);
    }

    @Override
    public void disPlay(String url, ImageView view, DisplayImageOptions options) {
        if (TextUtils.isEmpty(url)) return;
        if (view == null) return;
        ImageLoader.getInstance().displayImage(url, view, options);
    }
}
