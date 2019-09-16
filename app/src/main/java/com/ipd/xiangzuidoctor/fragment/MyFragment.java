package com.ipd.xiangzuidoctor.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.activity.AboutActivity;
import com.ipd.xiangzuidoctor.activity.CollectionActivity;
import com.ipd.xiangzuidoctor.activity.HospitialInfoActivity;
import com.ipd.xiangzuidoctor.activity.MsgActivity;
import com.ipd.xiangzuidoctor.activity.MyActivitiesActivity;
import com.ipd.xiangzuidoctor.activity.SettingActivity;
import com.ipd.xiangzuidoctor.activity.WalletActivity;
import com.ipd.xiangzuidoctor.activity.WebViewActivity;
import com.ipd.xiangzuidoctor.base.BaseFragment;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzuidoctor.common.config.IConstants.AVATAR;
import static com.ipd.xiangzuidoctor.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzuidoctor.common.config.IConstants.NIKE_NAME;
import static com.ipd.xiangzuidoctor.common.config.IConstants.PHONE;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：我的
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/9/1.
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.tv_is_certification)
    AppCompatTextView tvIsCertification;
    @BindView(R.id.tv_hospital_name)
    AppCompatTextView tvHospitalName;
    @BindView(R.id.tv_phone)
    AppCompatTextView tvPhone;
    @BindView(R.id.tv_my)
    TopView tvMy;
    @BindView(R.id.ib_top_customer_service)
    ImageButton ibTopCustomerService;
    @BindView(R.id.iv_hospital_head)
    RadiusImageView ivHospitalHead;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(false).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        ImmersionBar.setTitleBar(getActivity(), tvMy);
        ibTopCustomerService.setImageResource(R.drawable.ic_customer_service_white);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + SPUtil.get(getContext(), AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_test_head)).into(ivHospitalHead);
        tvHospitalName.setText(SPUtil.get(getContext(), NIKE_NAME, "") + "");
        tvIsCertification.setText("1".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "") + "") ? "未认证" : "认证了");
        tvPhone.setText(SPUtil.get(getContext(), PHONE, "") + "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_95:
                    Glide.with(this)
                            .load(BASE_LOCAL_URL + data.getStringExtra("modify_head"))
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    ivHospitalHead.setImageDrawable(resource);
                                }
                            });
                    tvHospitalName.setText(data.getStringExtra("modify_name"));
                    break;
            }
        }
    }

    @OnClick({R.id.cl_hospital_info, R.id.tv_wallet, R.id.tv_msg, R.id.tv_my_activities, R.id.tv_collection, R.id.tv_about, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_hospital_info:
                startActivityForResult(new Intent(getContext(), HospitialInfoActivity.class), REQUEST_CODE_95);
                break;
            case R.id.tv_wallet:
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.tv_msg:
                startActivity(new Intent(getContext(), MsgActivity.class));
                break;
            case R.id.tv_my_activities:
                startActivity(new Intent(getContext(), MyActivitiesActivity.class));
                break;
            case R.id.tv_collection:
                startActivity(new Intent(getContext(), CollectionActivity.class));
                break;
            case R.id.tv_about:
//                startActivity(new Intent(getContext(), AboutActivity.class));
                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 3));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }
}