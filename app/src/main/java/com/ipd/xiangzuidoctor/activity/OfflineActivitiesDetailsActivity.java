package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：活动详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class OfflineActivitiesDetailsActivity extends BaseActivity {

    @BindView(R.id.ll_enroll)
    LinearLayoutCompat llEnroll;
    @BindView(R.id.ll_cancel)
    LinearLayoutCompat llCancel;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_content)
    AppCompatTextView tvContent;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_label)
    AppCompatTextView tvLabel;
    @BindView(R.id.tv_offline_activities_details)
    TopView tvOfflineActivitiesDetails;
    @BindView(R.id.iv_img)
    RadiusImageView ivImg;

    private String offlineActivitiesType;//订单状态 0:最新活动， 1:我的活动

    @Override
    public int getLayoutId() {
        return R.layout.activity_offline_activities_details;
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
        ImmersionBar.setTitleBar(this, tvOfflineActivitiesDetails);

        offlineActivitiesType = getIntent().getStringExtra("offline_activities_type");
        switch (offlineActivitiesType) {
            case "0":
                llEnroll.setVisibility(View.VISIBLE);
                tvLabel.setText("进行中");
                tvLabel.setBackgroundResource(R.drawable.bg_label2);
                break;
            case "1":
                llCancel.setVisibility(View.VISIBLE);
                tvLabel.setText("进行中");
                tvLabel.setBackgroundResource(R.drawable.bg_label2);
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_enroll, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_enroll:
                startActivity(new Intent(this, EnrollActivity.class));
                break;
            case R.id.bt_cancel:
                startActivity(new Intent(this, AuthenticationResultActivity.class).putExtra("result_type", 4));
                break;
        }
    }
}
