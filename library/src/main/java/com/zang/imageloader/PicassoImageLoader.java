package com.zang.imageloader;

import android.content.Context;

/**
 * picasso 加载库
 */
public class PicassoImageLoader extends BaseImageLoader {
    private Context mContext;

    public PicassoImageLoader(Context context) {
        this.mContext = context.getApplicationContext();
    }
}
