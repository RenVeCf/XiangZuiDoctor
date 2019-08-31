package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;

import java.util.List;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/4.
 */
public class RecyclerViewBannerAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public RecyclerViewBannerAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_recyclerview_banner, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean s) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + "").apply(new RequestOptions().placeholder(R.mipmap.bg_test_banner)).into((AppCompatImageView) helper.getView(R.id.aciv_banner_item));
    }
}
