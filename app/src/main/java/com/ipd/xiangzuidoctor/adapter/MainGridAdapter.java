package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class MainGridAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public MainGridAdapter(@Nullable List<Integer> data) {
        super(R.layout.adapter_main_grid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        switch (item) {
            case 0:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_order)
                        .setText(R.id.tv_main_grid_item, "订单");
                break;
            case 1:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_special_column)
                        .setText(R.id.tv_main_grid_item, "医学专栏");
                break;
            case 2:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_offline_activities)
                        .setText(R.id.tv_main_grid_item, "线下活动");
                break;
            case 3:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_multipoint_practice)
                        .setText(R.id.tv_main_grid_item, "多点执业");
                break;
        }
    }
}
