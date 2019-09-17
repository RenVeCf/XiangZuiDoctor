package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.OfflineActivitiesListBean;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.List;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

public class OfflineActivitiesAdapter extends BaseQuickAdapter<OfflineActivitiesListBean.DataBean.ActivityListBean, BaseViewHolder> {


    public OfflineActivitiesAdapter(@Nullable List<OfflineActivitiesListBean.DataBean.ActivityListBean> data) {
        super(R.layout.adapter_offline_activities, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OfflineActivitiesListBean.DataBean.ActivityListBean item) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getLogo()).apply(new RequestOptions().placeholder(R.mipmap.bg_today_test)).into((AppCompatImageView) helper.getView(R.id.iv_title));

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getStartTime())
                .setText(R.id.tv_label, "进行中")
                .setBackgroundRes(R.id.tv_label, R.drawable.bg_label2)
                .setGone(R.id.bt_enroll, true)
                .setGone(R.id.bt_cancel, false)
                .addOnClickListener(R.id.bt_enroll)
                .addOnClickListener(R.id.bt_cancel);
    }
}
