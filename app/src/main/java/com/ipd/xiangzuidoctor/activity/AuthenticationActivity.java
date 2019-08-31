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
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_90;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_91;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_92;
import static com.ipd.xiangzuidoctor.common.config.IConstants.REQUEST_CODE_93;

/**
 * Description ：实名认证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class AuthenticationActivity extends BaseActivity {

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

    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
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
        ImmersionBar.setTitleBar(this, tvAuthentication);
    }

    @Override
    public void initData() {

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
                tvTitle.setRightString(listData.get(options1))
                        .setRightTextColor(getResources().getColor(R.color.black));
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
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add("...(主治医师)");
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_90:
                    tvPhoto.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_91:
                    tvIdCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_92:
                    tvQualificationsCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_93:
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
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "照片"), REQUEST_CODE_90);
                break;
            case R.id.tv_id_card: //身份证
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "身份证"), REQUEST_CODE_91);
                break;
            case R.id.tv_qualifications_card: //执业资格证/医师资格证
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "执业资格证/医师资格证"), REQUEST_CODE_92);
                break;
            case R.id.tv_chest_card: //胸牌
                startActivityForResult(new Intent(this, PhotoActivity.class).putExtra("title", "胸牌"), REQUEST_CODE_93);
                break;
            case R.id.bt_confirm:
                startActivity(new Intent(this, AuthenticationResultActivity.class).putExtra("result_type", 1));
                finish();
                break;
        }
    }
}
