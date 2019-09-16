package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.CollectionListBean;
import com.ipd.xiangzuidoctor.bean.SpecialColumnBean;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

public class SpecialColumnAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public SpecialColumnAdapter(@Nullable List<T> data) {
        super(R.layout.adapter_special_column, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (item instanceof SpecialColumnBean.DataBean.MedicalListBean) {
            Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + ((SpecialColumnBean.DataBean.MedicalListBean) item).getLogo()).apply(new RequestOptions().placeholder(R.mipmap.bg_special)).into((RadiusImageView) helper.getView(R.id.iv_img));

            helper.setText(R.id.tv_title, ((SpecialColumnBean.DataBean.MedicalListBean) item).getTitle())
                    .setText(R.id.tv_content, ((SpecialColumnBean.DataBean.MedicalListBean) item).getDescrib());
        } else if (item instanceof CollectionListBean.DataBean.CollectionListsBean) {
            Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + ((CollectionListBean.DataBean.CollectionListsBean) item).getLogo()).apply(new RequestOptions().placeholder(R.mipmap.bg_special)).into((RadiusImageView) helper.getView(R.id.iv_img));

            helper.setText(R.id.tv_title, ((CollectionListBean.DataBean.CollectionListsBean) item).getTitle())
                    .setText(R.id.tv_content, ((CollectionListBean.DataBean.CollectionListsBean) item).getDescrib());
        }
    }
}
