package goujiawang.gjw.bean;

import com.lidroid.xutils.db.annotation.Id;

/**
 * Created by hank on 2015/7/4.
 */
public abstract class EntryBase {
    @Id
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
