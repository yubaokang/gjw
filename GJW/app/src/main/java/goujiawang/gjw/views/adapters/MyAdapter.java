package goujiawang.gjw.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import goujiawang.gjw.R;
import goujiawang.gjw.bean.Actor;
import goujiawang.gjw.utils.BackgroundUtils;
import goujiawang.gjw.utils.ImageLoaderUtils;

/**
 * Created by hank on 2015/7/8.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Actor> actors;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    private DisplayImageOptions options;

    public MyAdapter(Context context, List<Actor> actors) {
        this.context = context;
        this.actors = actors;
        options = ImageLoaderUtils.getDisplayImageOption11111();
    }

    @Override
    public int getItemCount() {
        // 返回数据总数
        LogUtils.i("getItemCount");
        return actors == null ? 0 : actors.size();
    }

    @Override
    public int getItemViewType(int position) {
        LogUtils.i("position---" + position);
        // 最后一个item设置为footerView
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position + 1 == getItemCount()) {
            LogUtils.i("getItemViewType_footer");
            return TYPE_FOOTER;
        } else {
            LogUtils.i("onBindViewHolder_item");
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TYPE_HEADER) {
//            View v0 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_header, parent, false);
//            return new HeaderHolder(v0);
//        } else
        if (viewType == TYPE_ITEM) {
            LogUtils.i("onCreateViewHolder_Item");
            // 给ViewHolder设置布局文件
            View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment2_layout, parent, false);
            return new ItemHolder(v1);
        } else if (viewType == TYPE_FOOTER) {
            LogUtils.i("onCreateViewHolder_footer");
            View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_footer, parent, false);
            return new FooterHolder(v2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //holder.setIsRecyclable(false);
        LogUtils.i("onBindViewHolder");
        // 给ViewHolder设置元素
        if (holder instanceof ItemHolder) {
            Actor p = actors.get(position);
            ((ItemHolder) holder).mTextView.setText(p.name);
            BackgroundUtils.setBackground(context, ((ItemHolder) holder).mImageView, R.mipmap.cache_pic_normal);
            ImageLoader.getInstance().displayImage(p.picName, ((ItemHolder) holder).mImageView, options);
        }
        notifyItemInserted(0);
    }


    // 重写的自定义ViewHolder
    public class ItemHolder extends RecyclerView.ViewHolder {
        @ViewInject(R.id.name)
        public TextView mTextView;

        @ViewInject(R.id.pic)
        public ImageView mImageView;

        public ItemHolder(View v) {
            super(v);
            ViewUtils.inject(this, v);
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View v) {
            super(v);
        }
    }


    public class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View v) {
            super(v);
        }
    }
}
