package goujiawang.gjw.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import goujiawang.gjw.R;
import goujiawang.gjw.bean.Actor;
import goujiawang.gjw.utils.ImageLoaderUtils;

/**
 * Created by hank on 2015/7/9.
 */
public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<Actor> lists;
    DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOption11111();

    public ListAdapter(Context context, List<Actor> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Actor actor = lists.get(position);
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment2_layout, parent, false);
            ViewUtils.inject(mHolder, convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.name.setText(actor.name);
        ImageLoader.getInstance().displayImage(actor.picName, mHolder.pic, options);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.pic)
        public ImageView pic;

        @ViewInject(R.id.name)
        public TextView name;
    }
}
