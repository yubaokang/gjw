package goujiawang.gjw.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by hank on 2015/7/1.
 */
@Table(name = "actor")
public class Actor extends EntryBase {
    @Column(column = "name")
    public String name;

    @Column(column = "picName")
    public String picName;

    public Actor(String name, String picName) {
        this.name = name;
        this.picName = picName;
    }

//    public int getImageResourceId(Context context) {
//        try {
//            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
}
