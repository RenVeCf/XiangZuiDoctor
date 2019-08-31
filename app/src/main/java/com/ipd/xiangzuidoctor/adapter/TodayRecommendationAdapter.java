package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;

import java.util.List;

public class TodayRecommendationAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public TodayRecommendationAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_today_recommendation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setImageResource(R.id.iv_title, R.mipmap.bg_today_test)
                .setText(R.id.tv_title, "看病有学问，“寻医”有窍门")
                .setText(R.id.tv_content, "2019-05-12");
    }
}
