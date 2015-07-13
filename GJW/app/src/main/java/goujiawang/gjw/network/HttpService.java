package goujiawang.gjw.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import goujiawang.gjw.GJApplication;

/**
 * Created by hank on 2015/7/6.
 */
public class HttpService {
    public static String TAG = HttpService.class.getName();

    public static String DEFAULT_HTTP_TAG = "DEFAULT HTTP REQUEST";

    public static RequestQueue httpQueue;

    private static HttpService httpService;

    private HttpService(Context context) {
        httpQueue = Volley.newRequestQueue(context);
    }

    public static HttpService getHttpService() {
        if (httpService == null) {
            httpService = new HttpService(GJApplication.getContext());
        }
        return httpService;
    }

    public static HttpService newInstance(Context context) {
        if (httpService == null) {
            httpService = new HttpService(context);
        }
        return httpService;
    }

    /**
     * 往全局队列里加入一个新的http请求
     *
     * @param request
     */
    public <T> void addToRequestQueue(Request<T> request) {
        this.addToRequestQueue(request, DEFAULT_HTTP_TAG);
    }

    /**
     * 往全局队列里加入一个新的http请求
     *
     * @param request
     * @param tag     请求tag, 可以通过tag取消请求
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? DEFAULT_HTTP_TAG : tag);
        VolleyLog.d("Adding request to queue: %s", request.getUrl());
        if (httpQueue != null) {
            httpQueue.add(request);
        } else {
            Log.d(TAG, "http queue null");
            httpQueue = Volley.newRequestQueue(GJApplication.getContext());
        }
    }

    /**
     * 取消指定tag的请求
     *
     * @param tag
     */
    public void cancelRequests(Object tag) {
        if (this.httpQueue != null) {
            this.httpQueue.cancelAll(tag);
        }
    }
}
