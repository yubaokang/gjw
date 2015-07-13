package goujiawang.gjw.network;


import goujiawang.gjw.bean.message.MsgState;

/**
 * Created by hank on 2015/7/6.
 */
public interface ResponseListener {
    void onResponseSuccess(MsgState msgState);
}
