package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.List;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

public class TodayRecommendationAdapter extends BaseQuickAdapter<HomeBean.DataBean.ActivityListBean, BaseViewHolder> {

    public TodayRecommendationAdapter(@Nullable List<HomeBean.DataBean.ActivityListBean> data) {
        super(R.layout.adapter_today_recommendation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.ActivityListBean item) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.bg_today_test)).into((AppCompatImageView) helper.getView(R.id.iv_title));

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getStartTime());
    }
}
