/*
 * Copyright (c) 2014.
 * Jackrex
 */

package goujiawang.gjw.network;

import android.util.SparseBooleanArray;


import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lidroid.xutils.util.LogUtils;


import java.util.HashMap;
import java.util.Map;

import goujiawang.gjw.bean.message.MsgRequestBasic;
import goujiawang.gjw.bean.message.MsgResponseBasic;
import goujiawang.gjw.bean.message.MsgState;
import goujiawang.gjw.constant.GJConst;


/**
 * Created by hank on 2015/7/6.
 */
public class VolleyHttpClient {

    private static final String CLIENT_ID = ""; // replace
    // your
    // clientid;

    private static final String CLIENT_SECRET = "";

    public static String host = GJConst.SERVICE_URL;

    public static String getAbsoluteUrl(String relativeUrl) {
        return host + relativeUrl;
    }

    private static void httpPost(String url, Class clazz, Map<String, String> params, Response.Listener listener,
                                 Response.ErrorListener errorListener, int retryCount) {
        final JsonRequest<MsgRequestBasic> request = new JsonRequest<MsgRequestBasic>(Request.Method.POST, url, clazz, params, listener,
                errorListener, retryCount);
        HttpService.getHttpService().addToRequestQueue(request);
    }

    //一般用这个方法
    public static void httpPost(final int taskId, MsgRequestBasic messageEx, Class clazz, final ResponseListener listener) {
        httpPost(taskId, messageEx, clazz, listener, 1);
    }

    public static boolean isTaskRunning(int taskId) {
        return IS_TASK_RUNNING.get(taskId);
    }

    public final static SparseBooleanArray IS_TASK_RUNNING = new SparseBooleanArray();

    public static void httpPost(final int taskId, MsgRequestBasic messageEx, Class clazz, final ResponseListener listener, int retryCount) {
        IS_TASK_RUNNING.put(taskId, true);
        final Response.Listener<MsgResponseBasic> responseListener = new Response.Listener<MsgResponseBasic>() {
            @Override
            public void onResponse(MsgResponseBasic response) {
                IS_TASK_RUNNING.delete(taskId);
                if (listener != null) {
                    MsgState msgState = new MsgState();
                    msgState.setmResult(0);
                    msgState.setmTaskType(taskId);
                    if (null != response) {
                        msgState.setmResult(response.getResult());
                        msgState.setData(response);
                    }
                    listener.onResponseSuccess(msgState);
                }
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                IS_TASK_RUNNING.delete(taskId);
                MsgState msgState = new MsgState();
                msgState.setmResult(0);
                msgState.setmTaskType(taskId);
                if (listener != null) {
                    listener.onResponseSuccess(msgState);
                }
            }
        };
        final Map<String, String> params = new HashMap<String, String>();
        params.put("type", String.valueOf(messageEx.getType()));
        params.put("msg", JSON.toJSONString(messageEx));
        httpPost(getAbsoluteUrl(""), clazz, params, responseListener, errorListener, retryCount);
        LogUtils.i("volley request->" + JSON.toJSONString(messageEx));
    }
}
