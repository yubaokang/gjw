package goujiawang.gjw.bean.message;

/**
 * Created by hank on 2015/7/6.
 */
public class MsgRequestBasic {
    // 请求的时候同时赋值检测用
    public int type;
    //设备UUID
    public String uuid;
    // 登录用户证书
    public String cookie;
    //客户端当前版本号
    public String clientBuild;
    // 返回给客户端消息
    public String message;

    public MsgRequestBasic() {
        type = 1;
        uuid = "";
        cookie = "";
        clientBuild = "";
    }

    public int getType() {
        return type;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCookie() {
        return cookie;
    }

    public String getClientBuild() {
        return clientBuild;
    }

    public String getMessage() {
        return message;
    }

}
