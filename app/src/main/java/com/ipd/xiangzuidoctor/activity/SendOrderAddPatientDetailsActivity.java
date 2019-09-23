package com.ipd.xiangzuidoctor.activity;

import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;

/**
 * Description ：发单-添加患者信息详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/11.
 */
public class SendOrderAddPatientDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_add_patient_details)
    TopView tvSendOrderAddPatientDetails;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.stv_name)
    SuperTextView stvName;
    @BindView(R.id.stv_sex)
    SuperTextView stvSex;
    @BindView(R.id.stv_age)
    SuperTextView stvAge;
    @BindView(R.id.stv_height)
    SuperTextView stvHeight;
    @BindView(R.id.stv_body_weight)
    SuperTextView stvBodyWeight;
    @BindView(R.id.stv_anesthesia_type)
    SuperTextView stvAnesthesiaType;
    @BindView(R.id.stv_id_card)
    SuperTextView stvIdCard;
    @BindView(R.id.stv_insurance_consent)
    SuperTextView stvInsuranceConsent;
    @BindView(R.id.stv_surgery_about_medical_record)
    SuperTextView stvSurgeryAboutMedicalRecord;
    @BindView(R.id.stv_blood_routine)
    SuperTextView stvBloodRoutine;
    @BindView(R.id.stv_electrocardiogram)
    SuperTextView stvElectrocardiogram;
    @BindView(R.id.stv_coagulation)
    SuperTextView stvCoagulation;
    @BindView(R.id.stv_infectious_disease_index)
    SuperTextView stvInfectiousDiseaseIndex;

    private OrderDetailsBean.DataBean.OrderDetailBean orderDetailList;
    private int position;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_add_patient_details;
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
        ImmersionBar.setTitleBar(this, tvSendOrderAddPatientDetails);

        orderDetailList = getIntent().getParcelableExtra("patient_details");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    public void initData() {
        tvTopTitle.setText("患者" + position);
        stvName.setRightString(orderDetailList.getPatientName());
        stvSex.setRightString("1".equals(orderDetailList.getSex()) ? "男" : "女");
        stvAge.setRightString(orderDetailList.getAge() + "岁");
        stvHeight.setRightString(orderDetailList.getHeight() + "cm");
        stvBodyWeight.setRightString(orderDetailList.getWeight() + "kg");
        stvAnesthesiaType.setRightString(orderDetailList.getNarcosisType());

        if (!isEmpty(orderDetailList.getPositiveCard()) && !isEmpty(orderDetailList.getReverseCard()))
            stvIdCard.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailList.getInsurance()))
            stvInsuranceConsent.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailList.getSurgeryRelated()))
            stvSurgeryAboutMedicalRecord.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailList.getRoutineBlood()))
            stvBloodRoutine.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailList.getEcg()))
            stvElectrocardiogram.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailList.getCruor()))
            stvCoagulation.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailList.getContagion()))
            stvInfectiousDiseaseIndex.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.stv_id_card, R.id.stv_insurance_consent, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_id_card:
                break;
            case R.id.stv_insurance_consent:
                break;
            case R.id.stv_surgery_about_medical_record:
                break;
            case R.id.stv_blood_routine:
                break;
            case R.id.stv_electrocardiogram:
                break;
            case R.id.stv_coagulation:
                break;
            case R.id.stv_infectious_disease_index:
                break;
        }
    }
}
