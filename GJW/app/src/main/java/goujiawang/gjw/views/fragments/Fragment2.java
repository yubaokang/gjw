package goujiawang.gjw.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import goujiawang.gjw.R;
import goujiawang.gjw.constant.GJConst;
import goujiawang.gjw.views.adapters.ListAdapter;
import goujiawang.gjw.views.adapters.MyAdapter;
import goujiawang.gjw.bean.Actor;
import goujiawang.gjw.constant.TaskType;
import goujiawang.gjw.network.ResponseListener;
import goujiawang.gjw.network.VolleyHttpClient;
import goujiawang.gjw.bean.message.AARequest;
import goujiawang.gjw.bean.message.AAResponse;
import goujiawang.gjw.bean.message.MsgState;
import goujiawang.gjw.views.widgets.refreshlayout.PullRefreshLayout;


/**
 * Created by hank on 2015/7/3.
 */
public class Fragment2 extends BaseFragment implements ResponseListener {

//    @ViewInject(R.id.recyclerView)
//    private RecyclerView recyclerView;

//    @ViewInject(R.id.refreshLayout)
//    private PullRefreshLayout refreshLayout;

    @ViewInject(R.id.listView)
    private ListView listView;

    //@ViewInject(R.id.refreshLayout)
    //private SwipeRefreshLayout refreshLayout;

    private MyAdapter fragment2Adapter;
    //private Fragment2Adapter fragment2Adapter;

    private ListAdapter listAdapter;
    private View containerView;

    private List<Actor> actors = new ArrayList<>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (containerView == null) {
            containerView = inflater.inflate(R.layout.layout_fragment2, container, false);
            ViewUtils.inject(this, containerView);
            initView();
            query();
        }
        ViewGroup viewGroup = (ViewGroup) containerView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(containerView);
        }
        return containerView;
    }

    //网络访问请求
    public void query() {
        AARequest aaRequest = new AARequest();
        aaRequest.setPageIndex(0);
        aaRequest.setPageSize(10);
        VolleyHttpClient.httpPost(TaskType.AATASK, aaRequest, AAResponse.class, this);
    }

    private void initView() {
        for (int i = 0; i < GJConst.pics.length; i++) {
            Actor actor = new Actor(GJConst.names[i], GJConst.pics[i]);
            actors.add(actor);
        }
        listAdapter = new ListAdapter(getActivity(), actors);
        fragment2Adapter = new MyAdapter(getActivity(), actors);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        //设置动画样式
//        refreshLayout.setRefreshStyle(4);
//        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //pullRefreshLayout.setRefreshing(false);
//            }
//        });

//        refreshLayout.setColorSchemeColors(R.color.yellow);
//        // 这句话是为了，第一次进入页面的时候显示加载进度条
//        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
//    .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//.getDisplayMetrics()));

        //设置圆圈的背景色
        //swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.main_bg));

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 9);
        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);

//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(fragment2Adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listView.setAdapter(listAdapter);
    }

    @Override
    public void onResponseSuccess(MsgState msgState) {
        if (msgState == null) {
            return;
        }
        switch (msgState.getmTaskType()) {
            case TaskType.AATASK:
                //请求成功返回
                if (msgState.getmTaskType() == 1) {
                    AAResponse aaResponse = (AAResponse) msgState.getData();
                    DbUtils.DaoConfig config = new DbUtils.DaoConfig(this.getActivity());
                    DbUtils dbUtils = DbUtils.create(config);
                    config.setDbName("GJW");
                    config.setDbVersion(1);
                    dbUtils.configAllowTransaction(true);
                    try {
                        dbUtils.saveBindingIdAll(actors);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
