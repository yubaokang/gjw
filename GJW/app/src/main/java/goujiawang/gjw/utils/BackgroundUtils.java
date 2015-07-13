package goujiawang.gjw.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;

/**
 * Created by hank on 2015/7/5.
 */
public class BackgroundUtils {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(Context context, View v, int id) {
        if (hasJellyBean()) {
            v.setBackground(context.getResources().getDrawable(id));
        } else {
            v.setBackgroundDrawable(context.getResources().getDrawable(id));
        }
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }
}
