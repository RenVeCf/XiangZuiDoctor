package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.TestMultiItemEntityBean;

import java.util.List;

public class SpecialColumnAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public SpecialColumnAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_special_column, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setImageResource(R.id.iv_img, R.mipmap.bg_special)
                .setText(R.id.tv_title, "胸腔镜微创手术专栏")
                .setText(R.id.tv_content, "历史上外科治疗肺气肿的方法有：肋软骨切除松动胸壁、自主神经，肋软骨切除松动胸壁、自主神经，肋软骨切除松动胸壁、自主神经");
    }
}
