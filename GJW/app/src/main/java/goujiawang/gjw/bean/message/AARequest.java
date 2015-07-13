package goujiawang.gjw.bean.message;

import goujiawang.gjw.constant.MsgType;

/**
 * Created by hank on 2015/7/6.
 */
public class AARequest extends MsgRequestBasic {

    private int pageIndex;

    private int pageSize;

    public AARequest() {
        type = MsgType.AARequest;
    }

    public AARequest(int pageIndex, int pageSize) {
        type = MsgType.AARequest;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
