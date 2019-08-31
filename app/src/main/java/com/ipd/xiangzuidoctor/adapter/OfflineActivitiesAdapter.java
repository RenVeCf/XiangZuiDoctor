package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;

import java.util.List;

public class OfflineActivitiesAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    int offlineActivitiesType;

    public OfflineActivitiesAdapter(@Nullable List<TestMultiItemEntityBean> data, int offlineActivitiesType) {
        super(R.layout.adapter_offline_activities, data);
        this.offlineActivitiesType = offlineActivitiesType;
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setImageResource(R.id.iv_title, R.mipmap.bg_today_test)
                .setText(R.id.tv_title, "看病有学问，“寻医”有窍门")
                .setText(R.id.tv_content, "2019-05-12")
                .setText(R.id.tv_label, "进行中")
                .setBackgroundRes(R.id.tv_label, R.drawable.bg_label2)
                .setGone(R.id.bt_enroll, offlineActivitiesType == 0 ? true : false)
                .setGone(R.id.bt_cancel, offlineActivitiesType == 0 ? false : true)
                .addOnClickListener(R.id.bt_enroll)
                .addOnClickListener(R.id.bt_cancel);
    }
}
