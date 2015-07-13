package goujiawang.gjw.bean.message;

import android.util.Log;

import goujiawang.gjw.constant.GJConst;

/**
 * Created by hank on 2015/7/6.
 */
public class MsgResponseBasic {
    public int cityId;

    // 请求的时候同时赋值检测用
    public int type;

    // 回复的时候填写
    public int result;

    //设备UUID
    public String uuid;

    // 登录用户证书
    public String cookie;

    //客户端当前版本号
    public String clientBuild;

    public int appType;

    // 返回给客户端消息
    public String message;

    public MsgResponseBasic() {
        type = 1;
        result = 1;
        uuid = "";
        cookie = "";
        cityId = 1;
        clientBuild = "";
        appType = GJConst.CLIENT_TYPE;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getClientBuild() {
        return clientBuild;
    }

    public void setClientBuild(String clientBuild) {
        this.clientBuild = clientBuild;
    }

}
