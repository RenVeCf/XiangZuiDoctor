package com.ipd.xiangzuidoctor.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.ShareDialog;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：专栏详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class SpecialColumnDetailsActivity extends BaseActivity {

    @BindView(R.id.bt_collection)
    SuperButton btCollection;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_content)
    AppCompatTextView tvContent;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_special_column_details)
    TopView tvSpecialColumnDetails;
    @BindView(R.id.iv_img)
    RadiusImageView ivImg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special_column_details;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSpecialColumnDetails);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_share, R.id.bt_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_share:
                new ShareDialog(this){}.show();
                break;
            case R.id.bt_collection:
                if ("收藏".equals(btCollection.getText().toString()))
                    btCollection.setText("取消收藏");
                else
                    btCollection.setText("收藏");
                break;
        }
    }
}
