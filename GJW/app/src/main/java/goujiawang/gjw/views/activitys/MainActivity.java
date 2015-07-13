package goujiawang.gjw.views.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import goujiawang.gjw.R;
import goujiawang.gjw.views.fragments.Fragment1;
import goujiawang.gjw.views.fragments.Fragment2;
import goujiawang.gjw.views.fragments.Fragment3;
import goujiawang.gjw.utils.BackgroundUtils;
import goujiawang.gjw.views.fragments.Fragment4;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {

    @ViewInject(R.id.viewPager_main)
    private ViewPager viewPager_main;

    @ViewInject(android.R.id.tabhost)
    private FragmentTabHost mTabHost;

    // 定义一个布局
    private LayoutInflater layoutInflater;

    private Class fragmentArray[] = {Fragment1.class, Fragment2.class, Fragment3.class, Fragment4.class};

    private String mTextViewArray[] = {"首页", "商城", "我的", "其他"};

    private int iconUp[] = {
            R.mipmap.today_icon_up,
            R.mipmap.statistic_icon_up,
            R.mipmap.history_icon_up,
            R.mipmap.history_icon_up
    };

    private int iconDown[] = {
            R.mipmap.today_icon_down,
            R.mipmap.statistic_icon_down,
            R.mipmap.history_icon_down,
            R.mipmap.history_icon_down
    };

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", mTabHost.getCurrentTabTag());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initView();
        initPager();
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

    protected void initView() {
        layoutInflater = LayoutInflater.from(this);

        viewPager_main.addOnPageChangeListener(new ViewPagerListener());

        viewPager_main.setOffscreenPageLimit(3);

        mTabHost.setup(this, getSupportFragmentManager());
        mTabHost.setOnTabChangedListener(new TabHostListener());

        for (int i = 0; i < fragmentArray.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
        }
        ((LinearLayout) mTabHost.getTabWidget().getChildAt(0)).getChildAt(0).setBackgroundResource(iconDown[0]);
        ((TextView) ((LinearLayout) mTabHost.getTabWidget().getChildAt(0)).getChildAt(1)).setTextColor(getResources().getColor(R.color.tab_bg_down));
        viewPager_main.setCurrentItem(3);
    }

    private void initPager() {
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());
        viewPager_main.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        private ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item, null);
        ImageView imageView_tab = (ImageView) view.findViewById(R.id.imageView_tab);
        BackgroundUtils.setBackground(this, imageView_tab, iconUp[index]);
        TextView textView_tab = (TextView) view.findViewById(R.id.textView_tab);
        textView_tab.setText(mTextViewArray[index]);
        return view;
    }

    private class TabHostListener implements TabHost.OnTabChangeListener {
        @Override
        public void onTabChanged(String tabId) {
            int position = mTabHost.getCurrentTab();
            for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                //图片
                BackgroundUtils.setBackground(MainActivity.this, ((LinearLayout) mTabHost.getTabWidget().getChildAt(i)).getChildAt(0), iconUp[i]);
                //文字
                ((TextView) ((LinearLayout) mTabHost.getTabWidget().getChildAt(i)).getChildAt(1)).setTextColor(getResources().getColor(R.color.tab_bg_up));
            }
            BackgroundUtils.setBackground(MainActivity.this, ((LinearLayout) mTabHost.getTabWidget().getChildAt(position)).getChildAt(0), iconDown[position]);
            ((TextView) ((LinearLayout) mTabHost.getTabWidget().getChildAt(position)).getChildAt(1)).setTextColor(getResources().getColor(R.color.tab_bg_down));
            viewPager_main.setCurrentItem(position);
        }
    }


    class ViewPagerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageSelected(int position) {
            TabWidget widget = mTabHost.getTabWidget();
            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            mTabHost.setCurrentTab(position);
            widget.setDescendantFocusability(oldFocusability);
        }
    }
}
