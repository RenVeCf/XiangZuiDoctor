package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.adapter.PatientAdapter;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.GetOrderBean;
import com.ipd.xiangzuidoctor.bean.IngOperationEndBean;
import com.ipd.xiangzuidoctor.bean.IsArrivalsBean;
import com.ipd.xiangzuidoctor.bean.IsOrderOperationEndBean;
import com.ipd.xiangzuidoctor.bean.OperationStartBean;
import com.ipd.xiangzuidoctor.bean.OrderCancelBean;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.bean.OrderListBean;
import com.ipd.xiangzuidoctor.common.view.CallPhoneDialog;
import com.ipd.xiangzuidoctor.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.contract.OrderContract;
import com.ipd.xiangzuidoctor.presenter.OrderPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_96;
import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.utils.DateUtils.StartTimeToEndTime;
import static com.ipd.xiangzuidoctor.utils.DateUtils.timedate;
import static com.ipd.xiangzuidoctor.utils.isClickUtil.isFastClick;

/**
 * Description ：订单详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderDetailsActivity extends BaseActivity<OrderContract.View, OrderContract.Presenter> implements OrderContract.View {

    @BindView(R.id.tv_order_details)
    TopView tvOrderDetails;
    @BindView(R.id.tv_order_code)
    SuperTextView tvOrderCode;
    @BindView(R.id.tv_hospital_name)
    SuperTextView tvHospitalName;
    @BindView(R.id.tv_hospital_address)
    SuperTextView tvHospitalAddress;
    @BindView(R.id.tv_surgery_type)
    SuperTextView tvSurgeryType;
    @BindView(R.id.tv_simulated_surgery_name)
    SuperTextView tvSimulatedSurgeryName;
    @BindView(R.id.tv_start_time)
    SuperTextView tvStartTime;
    @BindView(R.id.tv_continued_time)
    SuperTextView tvContinuedTime;
    @BindView(R.id.tv_continued_fee)
    SuperTextView tvContinuedFee;
    @BindView(R.id.tv_ps)
    SuperTextView tvPs;
    //    @BindView(R.id.tv_patient_name)
//    SuperTextView tvPatientName;
//    @BindView(R.id.tv_patient_sex)
//    SuperTextView tvPatientSex;
//    @BindView(R.id.tv_patient_age)
//    SuperTextView tvPatientAge;
//    @BindView(R.id.tv_patient_height)
//    SuperTextView tvPatientHeight;
//    @BindView(R.id.tv_patient_body_weight)
//    SuperTextView tvPatientBodyWeight;
//    @BindView(R.id.tv_patient_simulated_anesthesia)
//    SuperTextView tvPatientSimulatedAnesthesia;
//    @BindView(R.id.tv_patient_id_card)
//    SuperTextView tvPatientIdCard;
    @BindView(R.id.tv_waiting_time)
    SuperTextView tvWaitingTime;
    //    @BindView(R.id.tv_patient_insurance_consent)
//    SuperTextView tvPatientInsuranceConsent;
//    @BindView(R.id.tv_patient_surgery_medical_record)
//    SuperTextView tvPatientSurgeryMedicalRecord;
//    @BindView(R.id.tv_patient_blood_routine)
//    SuperTextView tvPatientBloodRoutine;
//    @BindView(R.id.tv_patient_electrocardiogram)
//    SuperTextView tvPatientElectrocardiogram;
//    @BindView(R.id.tv_patient_coagulation)
//    SuperTextView tvPatientCoagulation;
//    @BindView(R.id.tv_patient_infectious_disease)
//    SuperTextView tvPatientInfectiousDisease;
    @BindView(R.id.ll_waiting_order)
    LinearLayoutCompat llWaitingOrder;
    @BindView(R.id.ll_end_operation)
    LinearLayoutCompat llEndOperation;
    @BindView(R.id.ll_is_order)
    LinearLayoutCompat llIsOrder;
    @BindView(R.id.rv_patient_list)
    RecyclerView rvPatientList;
    @BindView(R.id.tv_anesthesia_info)
    AppCompatTextView tvAnesthesiaInfo;
    @BindView(R.id.tv_patient_info)
    AppCompatTextView tvPatientInfo;
    @BindView(R.id.tv_fee_info)
    AppCompatTextView tvFeeInfo;
    @BindView(R.id.tv_anesthesia_tool)
    SuperTextView tvAnesthesiaTool;
    @BindView(R.id.tv_anesthesia_type)
    SuperTextView tvAnesthesiaType;
    @BindView(R.id.tv_anesthesia_sheet)
    SuperTextView tvAnesthesiaSheet;
    @BindView(R.id.tv_waiting_time_fee)
    SuperTextView tvWaitingTimeFee;
    @BindView(R.id.tv_surgery_fee)
    SuperTextView tvSurgeryFee;
    @BindView(R.id.tv_quicken_fee)
    SuperTextView tvQuickenFee;
    @BindView(R.id.tv_add_fee_fee)
    SuperTextView tvAddFeeFee;
    @BindView(R.id.tv_sum_fee)
    SuperTextView tvSumFee;
    @BindView(R.id.tv_pay_type)
    SuperTextView tvPayType;
    @BindView(R.id.bt_cancel_1)
    SuperButton btCancel1;
    @BindView(R.id.bt_call_doctor)
    SuperButton btCallDoctor;

    private String orderStatus;//1：待接单 2：待开始  3：进行中 4：已结束 5：待结算 6：已结算' 7：已取消 8.等待中
    private int orderId;
    private Handler handler;//等待时间计数
    private PatientAdapter patientAdapter;
    private List<OrderDetailsBean.DataBean.OrderDetailBean> orderDetail = new ArrayList<>();
    private SuperTextView selectPatient;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public OrderContract.Presenter createPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    public OrderContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvOrderDetails);

        orderStatus = getIntent().getStringExtra("order_status");
        orderId = getIntent().getIntExtra("orderId", 0);

        switch (orderStatus) {
            case "1":
                tvPatientInfo.setVisibility(View.GONE);
                llWaitingOrder.setVisibility(View.VISIBLE);
                break;
            case "2":
                llIsOrder.setVisibility(View.VISIBLE);
                rvPatientList.setVisibility(View.VISIBLE);
                btCallDoctor.setText("已到达");
                break;
            case "3":
                llEndOperation.setVisibility(View.VISIBLE);
//                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
//                tvAnesthesiaTool.setVisibility(View.VISIBLE);
//                tvAnesthesiaType.setVisibility(View.VISIBLE);
//
//                tvAnesthesiaTool.setRightString("已确认");
//                tvAnesthesiaType.setRightString("椎管内麻醉");

                tvWaitingTime.setVisibility(View.VISIBLE);
                rvPatientList.setVisibility(View.VISIBLE);
                break;
            case "4":
                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvFeeInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);
                tvAnesthesiaSheet.setVisibility(View.VISIBLE);
                tvWaitingTimeFee.setVisibility(View.VISIBLE);
                tvSurgeryFee.setVisibility(View.VISIBLE);
                tvQuickenFee.setVisibility(View.VISIBLE);
                tvAddFeeFee.setVisibility(View.VISIBLE);
                tvSumFee.setVisibility(View.VISIBLE);
                tvPayType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                tvWaitingTimeFee.setRightString("¥ 20元");
                tvSurgeryFee.setRightString("¥ 200元");
                tvQuickenFee.setRightString("¥ 30元");
                tvAddFeeFee.setRightString("¥ 50元");
                tvSumFee.setRightString("¥ 303元");
                tvPayType.setRightString("已结算");
                break;
            case "5":
                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvFeeInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);
                tvAnesthesiaSheet.setVisibility(View.VISIBLE);
                tvWaitingTimeFee.setVisibility(View.VISIBLE);
                tvSurgeryFee.setVisibility(View.VISIBLE);
                tvQuickenFee.setVisibility(View.VISIBLE);
                tvAddFeeFee.setVisibility(View.VISIBLE);
                tvSumFee.setVisibility(View.VISIBLE);
                tvPayType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                tvWaitingTimeFee.setRightString("¥ 20元");
                tvSurgeryFee.setRightString("¥ 200元");
                tvQuickenFee.setRightString("¥ 30元");
                tvAddFeeFee.setRightString("¥ 50元");
                tvSumFee.setRightString("¥ 303元");
                tvPayType.setRightString("已结算");
                break;
            case "6":
                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvFeeInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);
                tvAnesthesiaSheet.setVisibility(View.VISIBLE);
                tvWaitingTimeFee.setVisibility(View.VISIBLE);
                tvSurgeryFee.setVisibility(View.VISIBLE);
                tvQuickenFee.setVisibility(View.VISIBLE);
                tvAddFeeFee.setVisibility(View.VISIBLE);
                tvSumFee.setVisibility(View.VISIBLE);
                tvPayType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                tvWaitingTimeFee.setRightString("¥ 20元");
                tvSurgeryFee.setRightString("¥ 200元");
                tvQuickenFee.setRightString("¥ 30元");
                tvAddFeeFee.setRightString("¥ 50元");
                tvSumFee.setRightString("¥ 303元");
                tvPayType.setRightString("已结算");
                break;
            case "7":
                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvFeeInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);
                tvAnesthesiaSheet.setVisibility(View.VISIBLE);
                tvWaitingTimeFee.setVisibility(View.VISIBLE);
                tvSurgeryFee.setVisibility(View.VISIBLE);
                tvQuickenFee.setVisibility(View.VISIBLE);
                tvAddFeeFee.setVisibility(View.VISIBLE);
                tvSumFee.setVisibility(View.VISIBLE);
                tvPayType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                tvWaitingTimeFee.setRightString("¥ 20元");
                tvSurgeryFee.setRightString("¥ 200元");
                tvQuickenFee.setRightString("¥ 30元");
                tvAddFeeFee.setRightString("¥ 50元");
                tvSumFee.setRightString("¥ 303元");
                tvPayType.setRightString("已结算");
                break;
            case "8":
                llIsOrder.setVisibility(View.VISIBLE);
                tvWaitingTime.setVisibility(View.VISIBLE);
                rvPatientList.setVisibility(View.VISIBLE);
                btCancel1.setVisibility(View.GONE);
                btCallDoctor.setText("开始手术");
        }

        //患者信息
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvPatientList.setLayoutManager(layoutManager);
        rvPatientList.setNestedScrollingEnabled(false);
        rvPatientList.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvPatientList.setItemAnimator(new DefaultItemAnimator());//加载动画
    }

    @Override
    public void initData() {
        TreeMap<String, String> orderDetailsMap = new TreeMap<>();
        orderDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        orderDetailsMap.put("orderId", orderId + "");
        orderDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderDetailsMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOrderDetails(orderDetailsMap, true, false);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_96:
                    finish();
                    break;
            }
        }
    }

    @OnClick({R.id.bt_get_order, R.id.bt_cancel_1, R.id.bt_medical_record, R.id.bt_call_doctor, R.id.bt_end_operation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_order:
                if (isFastClick() && "1".equals(SPUtil.get(this, IS_SUPPLEMENT_INFO, "") + ""))
                    new TwoBtDialog(this, "请先实名认证后才可以接单", "去认证") {
                        @Override
                        public void confirm() {
                            startActivity(new Intent(getContext(), AuthenticationActivity.class));
                        }
                    }.show();
                break;
            case R.id.bt_cancel_1:
                if (isFastClick())
                    new TwoBtDialog(this, "取消订单将扣除订单的20%作为违约金", "确认") {
                        @Override
                        public void confirm() {
                            finish();
                        }
                    }.show();
                break;
            case R.id.bt_medical_record:
                new CallPhoneDialog(this) {
                }.show();
                break;
            case R.id.bt_call_doctor:
                if (isFastClick()) {
                    switch (orderStatus) {
                        case "1":
                            new TwoBtDialog(this, "是否确认已到达？", "温馨提示") {
                                @Override
                                public void confirm() {
                                    TreeMap<String, String> isArrivalsMap = new TreeMap<>();
                                    isArrivalsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                    isArrivalsMap.put("orderId", orderId + "");
                                    isArrivalsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isArrivalsMap.toString().replaceAll(" ", "") + SIGN)));
                                    getPresenter().getIsArrivals(isArrivalsMap, false, false);
                                }
                            }.show();
                            break;
                        case "8":
                            startActivityForResult(new Intent(this, StartOperationActivity.class).putExtra("title", "开始手术").putExtra("content", "麻醉器械、药品、急救设备及药品齐全").putExtra("orderId", orderId).putExtra("waitTime", tvWaitingTime.getLeftString().replaceAll("等待时间: ", "")), REQUEST_CODE_96);
                            break;
                    }
                }
                break;
            case R.id.bt_end_operation:
                if (isFastClick())
                    startActivity(new Intent(this, EndOperationActivity.class));
                break;
        }
    }

    @Override
    public void resultOrderList(OrderListBean data) {

    }

    @Override
    public void resultOrderDetails(OrderDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                tvOrderCode.setRightString(data.getData().getOrder().getOrderNo());
                tvHospitalName.setRightString(data.getData().getOrder().getHospitalName());
                tvHospitalAddress.setRightString(data.getData().getOrder().getAddress());
                tvSurgeryType.setRightString("1".equals(data.getData().getOrder().getOrderType()) ? "单台" : "连台");
                tvSimulatedSurgeryName.setRightString(data.getData().getOrder().getSurgeryName());
                tvStartTime.setRightString(data.getData().getOrder().getBeginTime());
                tvContinuedTime.setRightString(data.getData().getOrder().getDuration() + "h");
                tvContinuedFee.setRightString("¥ " + data.getData().getOrder().getExpectMoney() + "元");
                tvPs.setRightString(data.getData().getOrder().getPrompt());

                if ("8".equals(orderStatus)) {
                    handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            int endTime = Integer.parseInt(String.format("%010d", System.currentTimeMillis() / 1000));
                            String useTime = StartTimeToEndTime(data.getData().getOrder().getArriveTime(), timedate(endTime + ""), 1);
                            tvWaitingTime.setLeftString("等待时间: " + useTime);
                        }
                    };
                    MyThread thread = new MyThread();
                    thread.start();
                } else if ("3".equals(orderStatus)) {
                    handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            int endTime = Integer.parseInt(String.format("%010d", System.currentTimeMillis() / 1000));
                            String useTime = StartTimeToEndTime(data.getData().getOrder().getBeginTime(), timedate(endTime + ""), 1);
                            tvWaitingTime.setLeftString("手术时间: " + useTime);
                        }
                    };
                    MyThread thread = new MyThread();
                    thread.start();
                }

                if ("2".equals(orderStatus) || "3".equals(orderStatus) || "8".equals(orderStatus)) {
                    rvPatientList.setVisibility(View.VISIBLE);
                    orderDetail.clear();
                    orderDetail.addAll(data.getData().getOrderDetail());
                    patientAdapter = new PatientAdapter(orderDetail, "3".equals(orderStatus) ? 2 : 1);
                    rvPatientList.setAdapter(patientAdapter);
                    patientAdapter.bindToRecyclerView(rvPatientList);
                    patientAdapter.setEnableLoadMore(true);
                    patientAdapter.openLoadAnimation();
                    patientAdapter.disableLoadMoreIfNotFullPage();

                    patientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            startActivity(new Intent(OrderDetailsActivity.this, SendOrderAddPatientDetailsActivity.class).putExtra("patient_details", orderDetail.get(position)).putExtra("position", position + 1));

                            selectPatient = (SuperTextView) view;
                            selectPatient.setCheckBoxCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                }
                            });
                        }
                    });
                }

                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                handler.sendEmptyMessage(0);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void resultIsOrderOperationEnd(IsOrderOperationEndBean data) {

    }

    @Override
    public void resultIngOperationEnd(IngOperationEndBean data) {

    }

    @Override
    public void resultOperationStart(OperationStartBean data) {

    }

    @Override
    public void resultIsArrivals(IsArrivalsBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                finish();
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultOrderCancel(OrderCancelBean data) {

    }

    @Override
    public void resultGetOrder(GetOrderBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
