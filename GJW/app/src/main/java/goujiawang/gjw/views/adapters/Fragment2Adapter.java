package goujiawang.gjw.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Random;

import goujiawang.gjw.R;
import goujiawang.gjw.bean.Actor;
import goujiawang.gjw.bean.message.AARequest;
import goujiawang.gjw.bean.message.AAResponse;
import goujiawang.gjw.bean.message.MsgState;
import goujiawang.gjw.constant.TaskType;
import goujiawang.gjw.network.ResponseListener;
import goujiawang.gjw.network.VolleyHttpClient;
import goujiawang.gjw.utils.ImageLoaderUtils;

/**
 * Created by hank on 2015/7/8.
 */
public class Fragment2Adapter extends BaseRecyclerAdapter implements ResponseListener {
    private Context context;
    private List<Actor> actors;

    public Fragment2Adapter(Context context, List<Actor> actors) {
        this.context = context;
        this.actors = actors;
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_fragment2_layout;
    }

    @Override
    public void loadMore() {
        //加载更多接口调用
        AARequest aaRequest = new AARequest();
        aaRequest.setPageIndex(0);
        aaRequest.setPageSize(10);
        VolleyHttpClient.httpPost(TaskType.AATASK, aaRequest, AAResponse.class, this);
    }

    @Override
    public int getFooterLayout() {
        return R.layout.recycleview_footer;
    }

    @Override
    public int getItemCount() {
        return actors == null ? 0 : actors.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View v) {
        return new ItemViewHolder(v);
    }

    @Override
    public void setItemData(RecyclerView.ViewHolder holder, int position) {
        // 给ViewHolder设置元素
        if (holder instanceof ItemViewHolder) {
            Actor p = actors.get(position);
            ((ItemViewHolder) holder).mTextView.setText(p.name);
            ImageLoader.getInstance().displayImage("http://s1.hao123img.com/res/images/search_logo/web.png",
                    ((ItemViewHolder) holder).mImageView, ImageLoaderUtils.getDisplayImageOption8888(R.drawable.abc_ab_share_pack_mtrl_alpha));
        }
    }

    // 重写的自定义ViewHolder
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @ViewInject(R.id.name)
        public TextView mTextView;

        @ViewInject(R.id.pic)
        public ImageView mImageView;

        public ItemViewHolder(View v) {
            super(v);
            ViewUtils.inject(this, v);
        }
    }

    @Override
    public void onResponseSuccess(MsgState msgState) {
        //判断是否还有更多
        if (new Random().nextBoolean()) {
            notifyItemRemoved(getItemCount() - 1);
        } else {
            Actor actor = new Actor("余宝康", "余宝康");
            actors.add(actor);
            notifyDataSetChanged();
        }
    }
}
