package com.ipd.xiangzuidoctor.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

public class PatientAdapter extends BaseQuickAdapter<OrderDetailsBean.DataBean.OrderDetailBean, BaseViewHolder> {

    SuperTextView tvPatient;
    SuperTextView tvPatient1;
    int type;

    public PatientAdapter(@Nullable List<OrderDetailsBean.DataBean.OrderDetailBean> data, int type) {
        super(R.layout.adapter_patient, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailsBean.DataBean.OrderDetailBean item) {
        tvPatient = helper.getView(R.id.tv_patient);
        tvPatient1 = helper.getView(R.id.tv_patient_1);
        if (type == 2)
            tvPatient1.setVisibility(View.VISIBLE);
        else
            tvPatient.setVisibility(View.VISIBLE);
        tvPatient.setLeftString("患者" + (helper.getAdapterPosition() + 1))
                .setRightString(item.getPatientName());
        tvPatient1.setLeftString("患者" + (helper.getAdapterPosition() + 1))
                .setRightString(item.getPatientName());
        helper.addOnClickListener(R.id.tv_patient);
        helper.addOnClickListener(R.id.tv_patient_1);
    }
}