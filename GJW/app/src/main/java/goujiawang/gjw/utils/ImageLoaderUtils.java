package goujiawang.gjw.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import goujiawang.gjw.R;


/**
 * Created by hank on 2015/7/6.
 */
public class ImageLoaderUtils {
    /**
     * 初始化ImageLoader
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
//        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(480, 800)
//                .threadPoolSize(3)
//                .memoryCache(new LruMemoryCache(20 * 1024 * 1024))
//                .memoryCacheSize(20 * 1024 * 1024)
//                .diskCacheFileCount(200)
//                .diskCacheSize(50 * 1024 * 1024)
//                .build());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                        // .writeDebugLogs()
                        // Remove for release app
                .memoryCacheSizePercentage(20)
                .diskCacheSize(50 * 1024 * 1024)
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions getDisplayImageOptionDefault(int defaultImg) {
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(defaultImg)
                .showImageOnFail(defaultImg)
                .cacheInMemory(true).considerExifParams(false)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static DisplayImageOptions getDisplayImageOptions565(int defaultImg) {
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(defaultImg)
                .showImageOnFail(defaultImg)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    public static DisplayImageOptions getDisplayImageOption8888(int defaultImg) {
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(defaultImg)
                .showImageOnFail(defaultImg)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    /**
     * @param defaultImg 默认图片id
     * @param cornerPX   圆角像素px
     * @return
     */
    public static DisplayImageOptions getDisplayImageOptionRound(int defaultImg, int cornerPX) {
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(defaultImg)
                .showImageOnFail(defaultImg)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(cornerPX))
                .build();
    }

    public static DisplayImageOptions getDisplayImageOption11111() {
        return new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.cache_pic_normal)
                .showImageForEmptyUri(R.drawable.cache_pic_normal)
                .showImageOnFail(R.drawable.cache_pic_normal)
                .cacheInMemory(true)
                .showImageOnLoading(R.drawable.cache_pic_normal)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300))//设置图片渐显时间
                .build();
    }
}
