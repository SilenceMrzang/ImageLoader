# ImageLoader -> Factory #

> Android 常用的图片加载框架：

- Ul-ImageLoader
- Glid
- Picasso
- 等等 

> 直接Gradle 导入引用到项目的话 后期你会后悔，干脆写个中间层吧！创建一个工厂类，创建自己顺手的loader吧；侵入项目代码弱 后期好维护
> 这个操作很不错

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

### 检查导入包: ###

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

### 写个接口统一调用 ： ###

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

### 最后一步：配置你的加载框架-去吧皮卡丘 ###
