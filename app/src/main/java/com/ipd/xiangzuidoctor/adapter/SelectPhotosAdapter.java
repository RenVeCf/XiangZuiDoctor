package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/16.
 */
public class SelectPhotosAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SelectPhotosAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_select_photos, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(ApplicationUtil.getContext()).load(item).apply(new RequestOptions().placeholder(R.mipmap.bg_upload_img)).into((RadiusImageView) helper.getView(R.id.iv_img));
    }
}
