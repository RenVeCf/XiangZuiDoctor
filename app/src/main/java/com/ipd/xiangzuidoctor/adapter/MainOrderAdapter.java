package com.ipd.xiangzuidoctor.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.bean.HomeBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class MainOrderAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private SuperTextView stvName;
    private SuperTextView stvAddress;
    private SuperTextView stvStartTime;
    private SuperTextView stvFee;
    private String status; //订单状态：1：待接单 2：待开始  3：进行中4：已结束 5：待结算 6：已结算' 7：已取消 8.等待中
    private String surgeryName; //手术名称
    private String hospitalName; //医院名称
    private String address; //医院地址
    private String beginTime; //开始时间
    private double expectMoney; //预计费用

    public MainOrderAdapter(@Nullable List<T> data) {
        super(R.layout.adapter_main_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {

        if (item instanceof HomeBean.DataBean.AlreadyListBean) {
            status = ((HomeBean.DataBean.AlreadyListBean) item).getStatus();
            surgeryName = ((HomeBean.DataBean.AlreadyListBean) item).getSurgeryName();
            hospitalName = ((HomeBean.DataBean.AlreadyListBean) item).getHospitalName();
            address = ((HomeBean.DataBean.AlreadyListBean) item).getAddress();
            beginTime = ((HomeBean.DataBean.AlreadyListBean) item).getBeginTime();
            expectMoney = ((HomeBean.DataBean.AlreadyListBean) item).getExpectMoney();
        } else if (item instanceof HomeBean.DataBean.StayListBean) {
            status = ((HomeBean.DataBean.StayListBean) item).getStatus();
            surgeryName = ((HomeBean.DataBean.StayListBean) item).getSurgeryName();
            hospitalName = ((HomeBean.DataBean.StayListBean) item).getHospitalName();
            address = ((HomeBean.DataBean.StayListBean) item).getAddress();
            beginTime = ((HomeBean.DataBean.StayListBean) item).getBeginTime();
            expectMoney = ((HomeBean.DataBean.StayListBean) item).getExpectMoney();
        }
        String orderType = "";

        switch (status) {
            case "1":
                orderType = "待接单";
                helper.setText(R.id.bt_first, "查看详情")
                        .setGone(R.id.bt_second, false)
                        .setText(R.id.bt_third, "接单");
                break;
            case "2":
                orderType = "待开始";
                helper.setText(R.id.bt_first, "取消订单")
                        .setText(R.id.bt_second, "查看详情")
                        .setText(R.id.bt_third, "已到达");
                break;
            case "3":
                orderType = "进行中";
                helper.setText(R.id.bt_first, "查看详情")
                        .setGone(R.id.bt_second, false)
                        .setText(R.id.bt_third, "结束手术");
                break;
            case "5":
                orderType = "未结算";
                helper.setText(R.id.bt_first, "查看详情")
                        .setGone(R.id.bt_second, false)
                        .setGone(R.id.bt_third, false);
                break;
            case "6":
                orderType = "已结算";
                helper.setText(R.id.bt_first, "查看详情")
                        .setGone(R.id.bt_second, false)
                        .setGone(R.id.bt_third, false);
                break;
        }
        helper.setText(R.id.tv_lavlel1, "    " + "单台" + "   ")
                .setGone(R.id.tv_lavlel2, false)
                .setText(R.id.tv_lavlel2, "    " + "加价" + "   ")
                .setText(R.id.tv_name, surgeryName)
                .setText(R.id.tv_type, orderType)
                .addOnClickListener(R.id.cv_order_item)
                .addOnClickListener(R.id.stv_start_time)
                .addOnClickListener(R.id.stv_fee)
                .addOnClickListener(R.id.stv_name)
                .addOnClickListener(R.id.stv_address)
                .addOnClickListener(R.id.bt_first)
                .addOnClickListener(R.id.bt_second)
                .addOnClickListener(R.id.bt_third);

        stvName = helper.getView(R.id.stv_name);
        stvAddress = helper.getView(R.id.stv_address);
        stvStartTime = helper.getView(R.id.stv_start_time);
        stvFee = helper.getView(R.id.stv_fee);

        stvName.setLeftString("医院名称:")
                .setCenterString(hospitalName);
        stvAddress.setLeftString("医院地址:")
                .setCenterString(address);
        stvStartTime.setLeftString("开始时间:")
                .setCenterString(beginTime);
        stvFee.setLeftString("预计费用:")
                .setCenterString("¥ " + expectMoney + "元");
    }
}
