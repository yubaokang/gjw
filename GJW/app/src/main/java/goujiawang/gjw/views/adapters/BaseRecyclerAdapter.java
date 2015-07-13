package goujiawang.gjw.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hank on 2015/7/8.
 * <p/>
 * 封装RecyclerView.Adapter，将上拉加载更多（类似于footer）添加进去。
 * 子类MyAdapter只需实现抽象方法以及在子类MyAdapter内部写一个RecyclerView.ViewHolder子类：如下：
 * //public class ItemViewHolder extends RecyclerView.ViewHolder {
 * //    @ViewInject(R.id.name)
 * //    public TextView mTextView;
 * //
 * //    @ViewInject(R.id.pic)
 * //    public ImageView mImageView;
 * //
 * //    public ItemViewHolder(View v) {
 * //        super(v);
 * //        ViewUtils.inject(this, v);
 * //    }
 * //}
 */

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*判断是item还是footer*/
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    /*设置Item的layout*/
    public abstract int getItemLayout();

    /*设置footer的layout*/
    public abstract int getFooterLayout();

    /*设置Item中的数据*/
    public abstract void setItemData(RecyclerView.ViewHolder holder, int position);

    /*得到自定义的ItemViewHolder*/
    public abstract RecyclerView.ViewHolder getItemViewHolder(View v);

    /*加载更多*/
    public abstract void loadMore();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == TYPE_ITEM) {
            // 给ViewHolder设置布局文件
            v = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
            return getItemViewHolder(v);
        } else if (viewType == TYPE_FOOTER) {
            v = LayoutInflater.from(parent.getContext()).inflate(getFooterLayout(), parent, false);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new FooterHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 给ViewHolder设置元素
        setItemData(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            //加载更多
            loadMore();
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View v) {
            super(v);
        }
    }
}