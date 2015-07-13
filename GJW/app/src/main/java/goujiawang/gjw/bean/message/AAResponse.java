package goujiawang.gjw.bean.message;

import goujiawang.gjw.constant.MsgType;

/**
 * Created by hank on 2015/7/6.
 */

public class AAResponse extends MsgResponseBasic {
    //返回的是json字符串中的字段
    public AAResponse() {
        super();
        type = MsgType.AARespones;
    }
}
