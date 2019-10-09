package com.ipd.xiangzuidoctor.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.bean.GetUserInfoBean;
import com.ipd.xiangzuidoctor.bean.TitleListBean;
import com.ipd.xiangzuidoctor.bean.VerifiedBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.VerifiedContract;
import com.ipd.xiangzuidoctor.presenter.VerifiedPresenter;
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

import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_90;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_91;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_92;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_93;
import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;

/**
 * Description ：实名认证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class AuthenticationActivity extends BaseActivity<VerifiedContract.View, VerifiedContract.Presenter> implements VerifiedContract.View {

    @BindView(R.id.tv_authentication)
    TopView tvAuthentication;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.tv_title)
    SuperTextView tvTitle;
    @BindView(R.id.et_initial_hospital)
    EditText etInitialHospital;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_photo)
    SuperTextView tvPhoto;
    @BindView(R.id.tv_id_card)
    SuperTextView tvIdCard;
    @BindView(R.id.tv_qualifications_card)
    SuperTextView tvQualificationsCard;
    @BindView(R.id.tv_chest_card)
    SuperTextView tvChestCard;
    @BindView(R.id.bt_confirm)
    SuperButton btConfirm;

    private List<String> listData;
    private List<String> titleDataList = new ArrayList<>();//职位
    private List<TitleListBean.DataBean.TitleListsBean> titleListsBean = new ArrayList<>();//职位(取ID用)
    private OptionsPickerView pvOptions; //条件选择器
    private int titleId = 0;//职位ID
    private String photo, positiveUrl, negativeUrl, certificate, chestCard; //认证图片的返回链接

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public VerifiedContract.Presenter createPresenter() {
        return new VerifiedPresenter(this);
    }

    @Override
    public VerifiedContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAuthentication);
    }

    @Override
    public void initData() {
        TreeMap<String, String> getUserInfoMap = new TreeMap<>();
        getUserInfoMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        getUserInfoMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(getUserInfoMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getGetUserInfo(getUserInfoMap, false, false);

        TreeMap<String, String> titleListMap = new TreeMap<>();
        titleListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        titleListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(titleListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getTitleList(titleListMap, false, false);
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
                for (int i = 0; i < titleListsBean.size(); i++) {
                    if (titleListsBean.get(i).getTitleName().equals(listData.get(options1)))
                        titleId = titleListsBean.get(i).getTitleId();
                }
                tvTitle.setRightString(listData.get(options1))
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation));
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择职称");
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
        return titleDataList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_90:
                    photo = data.getStringExtra("imgUrl");
                    tvPhoto.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_91:
                    positiveUrl = data.getStringExtra("positiveUrl");
                    negativeUrl = data.getStringExtra("negativeUrl");
                    tvIdCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_92:
                    certificate = data.getStringExtra("imgUrl");
                    tvQualificationsCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_93:
                    chestCard = data.getStringExtra("imgUrl");
                    tvChestCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
            }
        }
    }

    @OnClick({R.id.tv_title, R.id.tv_photo, R.id.tv_id_card, R.id.tv_qualifications_card, R.id.tv_chest_card, R.id.bt_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title://职称
                showPickerView();
                break;
            case R.id.tv_photo: //照片
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "照片").putExtra("imgUrl", photo), REQUEST_CODE_90);
                break;
            case R.id.tv_id_card: //身份证
                startActivityForResult(new Intent(this, AgentCardActivity.class).putExtra("positiveUrl", positiveUrl).putExtra("negativeUrl", negativeUrl), REQUEST_CODE_91);
                break;
            case R.id.tv_qualifications_card: //执业资格证/医师资格证
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "执业资格证/医师资格证").putExtra("imgUrl", certificate), REQUEST_CODE_92);
                break;
            case R.id.tv_chest_card: //胸牌
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "胸牌").putExtra("imgUrl", chestCard), REQUEST_CODE_93);
                break;
            case R.id.bt_confirm:
                if (!isEmpty(etName.getText().toString().trim()) && !isEmpty(etDepartment.getText().toString().trim()) && titleId != 0 && !isEmpty(etInitialHospital.getText().toString().trim()) && !isEmpty(etPhone.getText().toString().trim()) && !isEmpty(photo) && !isEmpty(positiveUrl) && !isEmpty(negativeUrl) && !isEmpty(certificate) && !isEmpty(chestCard)) {
                    TreeMap<String, String> verifiedMap = new TreeMap<>();
                    verifiedMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    verifiedMap.put("truename", etName.getText().toString().trim());
                    verifiedMap.put("depart", etDepartment.getText().toString().trim());
                    verifiedMap.put("titleId", titleId + "");
                    verifiedMap.put("hospital", etInitialHospital.getText().toString().trim());
                    verifiedMap.put("contactNumber", etPhone.getText().toString().trim());
                    verifiedMap.put("photo", photo);
                    verifiedMap.put("positiveCard", positiveUrl);
                    verifiedMap.put("reverseCard", negativeUrl);
                    verifiedMap.put("certificate", certificate);
                    verifiedMap.put("chestCard", chestCard);
                    verifiedMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(verifiedMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getVerified(verifiedMap, true, false);
                } else
                    ToastUtil.showLongToast("请将信息填写完整！");
                break;
        }
    }

    @Override
    public void resultTitleList(TitleListBean data) {
        switch (data.getCode()) {
            case 200:
                titleListsBean.clear();
                titleListsBean.addAll(data.getData().getTitleList());
                for (TitleListBean.DataBean.TitleListsBean datas : data.getData().getTitleList()) {
                    titleDataList.add(datas.getTitleName());
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

    @Override
    public void resultVerified(VerifiedBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, AuthenticationResultActivity.class).putExtra("result_type", 1));
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
    public void resultGetUserInfo(GetUserInfoBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getData().getApproveStatus() == 2) {
                    switch (data.getData().getApprove().getStatus()){
                        case "1":
                            btConfirm.setText("待审核");
                            btConfirm.setEnabled(false);
                            etName.setText(data.getData().getUser().getNickname());
                            etName.setFocusable(false);
                            etDepartment.setText(data.getData().getApprove().getDepart());
                            etDepartment.setFocusable(false);
                            tvTitle.setRightString(data.getData().getApprove().getTitleName());
                            tvTitle.setEnabled(false);
                            etInitialHospital.setText(data.getData().getApprove().getHospital());
                            etInitialHospital.setFocusable(false);
                            etPhone.setText(data.getData().getApprove().getContactNumber());
                            etPhone.setFocusable(false);
                            photo = data.getData().getApprove().getPhoto();
                            tvPhoto.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            positiveUrl = data.getData().getApprove().getPositiveCard();
                            negativeUrl = data.getData().getApprove().getReverseCard();
                            tvIdCard.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            certificate = data.getData().getApprove().getCertificate();
                            tvQualificationsCard.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            chestCard = data.getData().getApprove().getChestCard();
                            tvChestCard.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            break;
                        case "2":
                            btConfirm.setText("审核通过");
                            btConfirm.setEnabled(false);
                            etName.setText(data.getData().getUser().getNickname());
                            etName.setFocusable(false);
                            etDepartment.setText(data.getData().getApprove().getDepart());
                            etDepartment.setFocusable(false);
                            tvTitle.setRightString(data.getData().getApprove().getTitleName());
                            tvTitle.setEnabled(false);
                            etInitialHospital.setText(data.getData().getApprove().getHospital());
                            etInitialHospital.setFocusable(false);
                            etPhone.setText(data.getData().getApprove().getContactNumber());
                            etPhone.setFocusable(false);
                            photo = data.getData().getApprove().getPhoto();
                            tvPhoto.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            positiveUrl = data.getData().getApprove().getPositiveCard();
                            negativeUrl = data.getData().getApprove().getReverseCard();
                            tvIdCard.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            certificate = data.getData().getApprove().getCertificate();
                            tvQualificationsCard.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            chestCard = data.getData().getApprove().getChestCard();
                            tvChestCard.setRightString("已上传")
                                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                            break;
                        case "3":
                            ToastUtil.showShortToast(data.getData().getApprove().getAuditContent());
                            btConfirm.setText("重新审核");
                            break;
                    }
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

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
