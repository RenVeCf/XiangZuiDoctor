package com.ipd.xiangzuidoctor.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.AnesthesiaListBean;
import com.ipd.xiangzuidoctor.bean.GetOrderBean;
import com.ipd.xiangzuidoctor.bean.IngOperationEndBean;
import com.ipd.xiangzuidoctor.bean.IsArrivalsBean;
import com.ipd.xiangzuidoctor.bean.IsOrderOperationEndBean;
import com.ipd.xiangzuidoctor.bean.OperationStartBean;
import com.ipd.xiangzuidoctor.bean.OrderCancelBean;
import com.ipd.xiangzuidoctor.bean.OrderDetailsBean;
import com.ipd.xiangzuidoctor.bean.OrderListBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.common.view.TwoBtDialog;
import com.ipd.xiangzuidoctor.contract.OrderContract;
import com.ipd.xiangzuidoctor.presenter.OrderPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_94;
import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.utils.isClickUtil.isFastClick;

/**
 * Description ：结束手术
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/31.
 */
public class EndOperationActivity extends BaseActivity<OrderContract.View, OrderContract.Presenter> implements OrderContract.View {

    @BindView(R.id.tv_end_operation)
    TopView tvEndOperation;
    @BindView(R.id.tv_anesthesia_type)
    SuperTextView tvAnesthesiaType;
    @BindView(R.id.tv_anesthesia_sheet)
    SuperTextView tvAnesthesiaSheet;
    @BindView(R.id.tv_estimated_time)
    SuperTextView tvEstimatedTime;
    @BindView(R.id.tv_estimated_fee)
    SuperTextView tvEstimatedFee;
    @BindView(R.id.tv_waiting_time)
    SuperTextView tvWaitingTime;
    @BindView(R.id.tv_overtime_fee)
    SuperTextView tvOvertimeFee;
    @BindView(R.id.tv_actual_time)
    SuperTextView tvActualTime;
    @BindView(R.id.tv_actual_fee)
    SuperTextView tvActualFee;

    private List<String> listData;
    private List<String> anesthesiaDataList = new ArrayList<>();//麻醉方式
    private OptionsPickerView pvOptions; //条件选择器
    private int orderDetailId, orderId, lastPatient;

    @Override
    public int getLayoutId() {
        return R.layout.activity_end_operation;
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
        ImmersionBar.setTitleBar(this, tvEndOperation);

        orderDetailId = getIntent().getIntExtra("orderDetailId", 0);
        orderId = getIntent().getIntExtra("orderId", 0);
        lastPatient = getIntent().getIntExtra("lastPatient", 0);
        if (lastPatient == 1) {
            tvEstimatedTime.setVisibility(View.VISIBLE);
            tvEstimatedFee.setVisibility(View.VISIBLE);
            tvWaitingTime.setVisibility(View.VISIBLE);
            tvOvertimeFee.setVisibility(View.VISIBLE);
            tvActualTime.setVisibility(View.VISIBLE);
            tvActualFee.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> anesthesiaListMap = new TreeMap<>();
        anesthesiaListMap.put("userId", SPUtil.get(EndOperationActivity.this, USER_ID, "") + "");
        anesthesiaListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(anesthesiaListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getAnesthesiaList(anesthesiaListMap, false, false);

        if (lastPatient == 1) {
            TreeMap<String, String> orderDetailsMap = new TreeMap<>();
            orderDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
            orderDetailsMap.put("orderId", orderId + "");
            orderDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderDetailsMap.toString().replaceAll(" ", "") + SIGN)));
            getPresenter().getOrderDetails(orderDetailsMap, true, false);
        }
    }

    @Override
    public void initListener() {

    }

    //条件选择器
    private void showPickerView() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        listData = getTitleData();
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvAnesthesiaType.setRightString(listData.get(options1))
                        .setRightTextColor(getResources().getColor(R.color.black));
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择麻醉方式");
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setDecorView(getWindow().getDecorView().findViewById(android.R.id.content))
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getTitleData() {
        return anesthesiaDataList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_94:
                    tvAnesthesiaSheet.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.tv_anesthesia_type, R.id.tv_anesthesia_sheet, R.id.tv_patient_handover, R.id.bt_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.tv_anesthesia_type:
                showPickerView();
                break;
            case R.id.tv_anesthesia_sheet:
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "麻醉单"), REQUEST_CODE_94);
                break;
            case R.id.tv_patient_handover:
                new TwoBtDialog(this, "请完成病人交接", "确认") {
                    @Override
                    public void confirm() {
                        startActivity(new Intent(getContext(), StartOperationActivity.class).putExtra("title", "病人交接").putExtra("content", "病人已完成交接"));
                    }
                }.show();
                break;
            case R.id.bt_confirm:
                if (isFastClick()) {
                    TreeMap<String, String> isOrderOperationEndMap = new TreeMap<>();
                    isOrderOperationEndMap.put("userId", SPUtil.get(EndOperationActivity.this, USER_ID, "") + "");
                    isOrderOperationEndMap.put("orderDetailId", orderDetailId + "");
                    isOrderOperationEndMap.put("orderId", orderId + "");
                    isOrderOperationEndMap.put("anestxMode", tvAnesthesiaType.getRightString());
                    isOrderOperationEndMap.put("narcosisForm", "");
                    isOrderOperationEndMap.put("handover", "");
                    isOrderOperationEndMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isOrderOperationEndMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getIsOrderOperationEnd(isOrderOperationEndMap, false, false);
                }
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
                tvEstimatedTime.setRightString(data.getData().getOrder().getDuration() + "小时");
                tvEstimatedFee.setRightString("¥" + data.getData().getOrder().getExpectMoney());
                tvWaitingTime.setRightString(data.getData().getOrder().getWaitTime());
                tvOvertimeFee.setRightString("¥" + data.getData().getOrder().getOvertimeMoney());
                tvActualTime.setRightString(data.getData().getOrder().getSurgeryTime() + "小时");
                tvActualFee.setRightString("¥" + data.getData().getOrder().getSurgeryMoney());
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

    @Override
    public void resultIsOrderOperationEnd(IsOrderOperationEndBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
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

    @Override
    public void resultIngOperationEnd(IngOperationEndBean data) {

    }

    @Override
    public void resultOperationStart(OperationStartBean data) {

    }

    @Override
    public void resultIsArrivals(IsArrivalsBean data) {

    }

    @Override
    public void resultOrderCancel(OrderCancelBean data) {

    }

    @Override
    public void resultGetOrder(GetOrderBean data) {

    }

    @Override
    public void resultAnesthesiaList(AnesthesiaListBean data) {
//        switch (data.getCode()) {
//            case 200:
//                titleListsBean.clear();
//                titleListsBean.addAll(data.getData().getTitleList());
//                for (TitleListBean.DataBean.TitleListsBean datas : data.getData().getTitleList()) {
//                    titleDataList.add(datas.getTitleName());
//                }
//                break;
//            case 900:
//                ToastUtil.showLongToast(data.getMsg());
//                //清除所有临时储存
//                SPUtil.clear(ApplicationUtil.getContext());
//                ApplicationUtil.getManager().finishActivity(MainActivity.class);
//                startActivity(new Intent(this, CaptchaLoginActivity.class));
//                finish();
//                break;
//        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
