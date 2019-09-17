package com.ipd.xiangzuidoctor.adapter;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.utils.L;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
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

        tvPatient.setLeftString("患者" + (helper.getAdapterPosition() + 1))
                .setRightString(item.getPatientName());
        tvPatient1.setLeftString("患者" + (helper.getAdapterPosition() + 1))
                .setRightString(item.getPatientName());

        if (type == 2) {
            tvPatient1.setVisibility(View.VISIBLE);
            if (item.isSelectPatient() || "2".equals(item.getStatus()))
                tvPatient1.setCheckBoxChecked(true, true);
            else
                tvPatient1.setCheckBoxChecked(false, true);
        } else
            tvPatient.setVisibility(View.VISIBLE);

        tvPatient1.setCheckBoxCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ("2".equals(item.getStatus())) {
                    tvPatient1.setCheckBoxChecked(true, true);
                    ToastUtil.showShortToast("该手术已结束！");
                }
            }
        });
        helper.addOnClickListener(R.id.tv_patient);
        helper.addOnClickListener(R.id.tv_patient_1);
    }
}