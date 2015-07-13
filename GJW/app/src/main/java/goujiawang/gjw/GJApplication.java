package goujiawang.gjw;

import android.app.Application;
import android.content.Context;

import goujiawang.gjw.network.HttpService;
import goujiawang.gjw.utils.ImageLoaderUtils;


/**
 * Created by hank on 2015/7/6.
 */
public class GJApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        HttpService.newInstance(context);
        ImageLoaderUtils.initImageLoader(context);
    }

    public static Context getContext() {
        return context;
    }
}
