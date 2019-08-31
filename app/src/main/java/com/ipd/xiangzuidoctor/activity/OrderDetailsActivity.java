package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.CallPhoneDialog;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzuidoctor.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;
import static com.ipd.xiangzuidoctor.utils.isClickUtil.isFastClick;

/**
 * Description ：订单详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_order_details)
    TopView tvOrderDetails;
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
    @BindView(R.id.tv_patient_name)
    SuperTextView tvPatientName;
    @BindView(R.id.tv_patient_sex)
    SuperTextView tvPatientSex;
    @BindView(R.id.tv_patient_age)
    SuperTextView tvPatientAge;
    @BindView(R.id.tv_patient_height)
    SuperTextView tvPatientHeight;
    @BindView(R.id.tv_patient_body_weight)
    SuperTextView tvPatientBodyWeight;
    @BindView(R.id.tv_patient_simulated_anesthesia)
    SuperTextView tvPatientSimulatedAnesthesia;
    @BindView(R.id.tv_patient_id_card)
    SuperTextView tvPatientIdCard;
    @BindView(R.id.tv_patient_insurance_consent)
    SuperTextView tvPatientInsuranceConsent;
    @BindView(R.id.tv_patient_surgery_medical_record)
    SuperTextView tvPatientSurgeryMedicalRecord;
    @BindView(R.id.tv_patient_blood_routine)
    SuperTextView tvPatientBloodRoutine;
    @BindView(R.id.tv_patient_electrocardiogram)
    SuperTextView tvPatientElectrocardiogram;
    @BindView(R.id.tv_patient_coagulation)
    SuperTextView tvPatientCoagulation;
    @BindView(R.id.tv_patient_infectious_disease)
    SuperTextView tvPatientInfectiousDisease;
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

    private int surgeryType;//1: 单台，2: 连台
    private String orderType;//订单状态 0:待接单， 1:已接单， 2:进行中， 3:已完成
    //    private SelectOrderAddPatientAdapter selectOrderAddPatientAdapter;
//    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvOrderDetails);

        surgeryType = getIntent().getIntExtra("surgery_type", 0);
        orderType = getIntent().getStringExtra("order_type");

        switch (orderType) {
            case "0":
                tvPatientName.setVisibility(View.GONE);
                tvPatientSex.setVisibility(View.GONE);
                tvPatientAge.setVisibility(View.GONE);
                tvPatientHeight.setVisibility(View.GONE);
                tvPatientBodyWeight.setVisibility(View.GONE);
                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
                tvPatientIdCard.setVisibility(View.GONE);
                tvPatientInsuranceConsent.setVisibility(View.GONE);
                tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
                tvPatientBloodRoutine.setVisibility(View.GONE);
                tvPatientElectrocardiogram.setVisibility(View.GONE);
                tvPatientCoagulation.setVisibility(View.GONE);
                tvPatientInfectiousDisease.setVisibility(View.GONE);
                tvPatientInfo.setVisibility(View.GONE);
                llWaitingOrder.setVisibility(View.VISIBLE);
                break;
            case "1":
                llIsOrder.setVisibility(View.VISIBLE);
                break;
            case "2":
//                tvPatientName.setVisibility(View.GONE);
//                tvPatientSex.setVisibility(View.GONE);
//                tvPatientAge.setVisibility(View.GONE);
//                tvPatientHeight.setVisibility(View.GONE);
//                tvPatientBodyWeight.setVisibility(View.GONE);
//                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
//                tvPatientIdCard.setVisibility(View.GONE);
//                tvPatientInsuranceConsent.setVisibility(View.GONE);
//                tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
//                tvPatientBloodRoutine.setVisibility(View.GONE);
//                tvPatientElectrocardiogram.setVisibility(View.GONE);
//                tvPatientCoagulation.setVisibility(View.GONE);
//                tvPatientInfectiousDisease.setVisibility(View.GONE);
                llEndOperation.setVisibility(View.VISIBLE);

                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                break;
            case "3":
//                tvPatientName.setVisibility(View.GONE);
//                tvPatientSex.setVisibility(View.GONE);
//                tvPatientAge.setVisibility(View.GONE);
//                tvPatientHeight.setVisibility(View.GONE);
//                tvPatientBodyWeight.setVisibility(View.GONE);
//                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
//                tvPatientIdCard.setVisibility(View.GONE);
//                tvPatientInsuranceConsent.setVisibility(View.GONE);
//                tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
//                tvPatientBloodRoutine.setVisibility(View.GONE);
//                tvPatientElectrocardiogram.setVisibility(View.GONE);
//                tvPatientCoagulation.setVisibility(View.GONE);
//                tvPatientInfectiousDisease.setVisibility(View.GONE);
//                rvPatientList.setVisibility(View.VISIBLE);

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
        }

//        //更多订单
//        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
//        rvPatientList.setLayoutManager(layoutManager);
//        rvPatientList.setNestedScrollingEnabled(false);
//        rvPatientList.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
//        rvPatientList.setItemAnimator(new DefaultItemAnimator());//加载动画
//
//        for (int i = 0; i < 2; i++) {
//            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
//            str1.add(testData);
//        }
//        rvPatientList.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(str1, 2));
//        selectOrderAddPatientAdapter.bindToRecyclerView(rvPatientList);
//        selectOrderAddPatientAdapter.openLoadAnimation();
    }

    @Override
    public void initData() {
        tvHospitalName.setRightString("上海市第一人民医院");
        tvHospitalAddress.setRightString("上海市虹口区海宁路100号");
        tvSurgeryType.setRightString(surgeryType == 1 ? "单台" : "连台");
        tvSimulatedSurgeryName.setRightString("阑尾切除术");
        tvStartTime.setRightString("2019-05-05 10:30");
        tvContinuedTime.setRightString("2h");
        tvContinuedFee.setRightString("¥ 300元");
        tvPs.setRightString("高龄、药物过敏");
        if (surgeryType == 1) {
            tvPatientName.setRightString("李先生");
            tvPatientSex.setRightString("男");
            tvPatientAge.setRightString("22岁");
            tvPatientHeight.setRightString("172cm");
            tvPatientBodyWeight.setRightString("66kg");
            tvPatientSimulatedAnesthesia.setRightString("椎管内麻醉");
            tvPatientIdCard.setRightString("已上传");
            tvPatientInsuranceConsent.setRightString("已上传");
            tvPatientSurgeryMedicalRecord.setRightString("已上传");
            tvPatientBloodRoutine.setRightString("已上传");
            tvPatientElectrocardiogram.setRightString("已上传");
            tvPatientCoagulation.setRightString("已上传");
            tvPatientInfectiousDisease.setRightString("已上传");
        } else {
            tvPatientName.setVisibility(View.GONE);
            tvPatientSex.setVisibility(View.GONE);
            tvPatientAge.setVisibility(View.GONE);
            tvPatientHeight.setVisibility(View.GONE);
            tvPatientBodyWeight.setVisibility(View.GONE);
            tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
            tvPatientIdCard.setVisibility(View.GONE);
            tvPatientInsuranceConsent.setVisibility(View.GONE);
            tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
            tvPatientBloodRoutine.setVisibility(View.GONE);
            tvPatientElectrocardiogram.setVisibility(View.GONE);
            tvPatientCoagulation.setVisibility(View.GONE);
            tvPatientInfectiousDisease.setVisibility(View.GONE);
            rvPatientList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initListener() {
//        selectOrderAddPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()) {
//                    case R.id.stv_add_patient_item:
//                        startActivity(new Intent(OrderDetailsActivity.this, SendOrderAddPatientDetailsActivity.class));
//                        break;
//                }
//            }
//        });
    }

    @OnClick({R.id.bt_get_order, R.id.bt_cancel_1, R.id.bt_medical_record, R.id.bt_call_doctor, R.id.bt_end_operation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_order:
                if (isFastClick() && isEmpty(SPUtil.get(this, IS_SUPPLEMENT_INFO, "") + ""))
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
                if (isFastClick())
                    startActivity(new Intent(this, StartOperationActivity.class).putExtra("title", "开始手术").putExtra("content", "麻醉器械、药品、急救设备及药品齐全"));
                break;
            case R.id.bt_end_operation:
                if (isFastClick())
                    startActivity(new Intent(this, EndOperationActivity.class));
                break;
        }
    }
}
