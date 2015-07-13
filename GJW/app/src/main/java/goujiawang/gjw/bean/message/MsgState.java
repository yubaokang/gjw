package goujiawang.gjw.bean.message;

/**
 * Created by hank on 2015/7/6.
 */

public class MsgState {

    //返回结果码：例如0-错误，1-正确
    private int mResult;

    private int mTaskType;

    private Object data;

    public void setmResult(int mResult) {
        this.mResult = mResult;
    }

    public void setmTaskType(int mTaskType) {
        this.mTaskType = mTaskType;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getmResult() {
        return mResult;
    }

    public int getmTaskType() {
        return mTaskType;
    }

    public Object getData() {
        return data;
    }
}
