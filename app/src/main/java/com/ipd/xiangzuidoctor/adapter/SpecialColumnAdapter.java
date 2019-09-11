package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

public class SpecialColumnAdapter extends BaseQuickAdapter<SpecialColumnBean.DataBean.MedicalListBean, BaseViewHolder> {

    public SpecialColumnAdapter(@Nullable List<SpecialColumnBean.DataBean.MedicalListBean> data) {
        super(R.layout.adapter_special_column, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialColumnBean.DataBean.MedicalListBean item) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getLogo()).apply(new RequestOptions().placeholder(R.mipmap.bg_special)).into((RadiusImageView) helper.getView(R.id.iv_img));

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getDescrib());
    }
}
