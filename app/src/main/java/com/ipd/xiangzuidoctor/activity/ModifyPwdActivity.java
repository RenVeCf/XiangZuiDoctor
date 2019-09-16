package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.bean.ModifyUserPwdBean;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.contract.LoginContract;
import com.ipd.xiangzuidoctor.contract.ModifyUserPwdContract;
import com.ipd.xiangzuidoctor.presenter.LoginPresenter;
import com.ipd.xiangzuidoctor.presenter.ModifyUserPwdPresenter;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.ipd.xiangzuidoctor.utils.MD5Utils;
import com.ipd.xiangzuidoctor.utils.SPUtil;
import com.ipd.xiangzuidoctor.utils.StringUtils;
import com.ipd.xiangzuidoctor.utils.ToastUtil;
import com.xuexiang.xui.widget.edittext.PasswordEditText;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzuidoctor.common.config.IConstants.AVATAR;
import static com.ipd.xiangzuidoctor.common.config.IConstants.NIKE_NAME;
import static com.ipd.xiangzuidoctor.common.config.IConstants.SIGN;
import static com.ipd.xiangzuidoctor.common.config.IConstants.USER_ID;
import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.xiangzuidoctor.utils.StringUtils.isEmpty;

/**
 * Description ：修改密码
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class ModifyPwdActivity extends BaseActivity<ModifyUserPwdContract.View, ModifyUserPwdContract.Presenter> implements ModifyUserPwdContract.View {

    @BindView(R.id.tv_modify_pwd)
    TopView tvModifyPwd;
    @BindView(R.id.new_pwd)
    PasswordEditText newPwd;
    @BindView(R.id.new_pwd_again)
    PasswordEditText newPwdAgain;
    @BindView(R.id.old_pwd)
    PasswordEditText oldPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public ModifyUserPwdContract.Presenter createPresenter() {
        return new ModifyUserPwdPresenter(this);
    }

    @Override
    public ModifyUserPwdContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyPwd);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.bt_confirm)
    public void onViewClicked() {
        TreeMap<String, String> modifyUserPwdMap = new TreeMap<>();
        modifyUserPwdMap.put("userId", SPUtil.get(this, USER_ID, "") +"");
        modifyUserPwdMap.put("orgPassword", oldPwd.getText().toString().trim());
        modifyUserPwdMap.put("newPassword", newPwd.getText().toString().trim());
        modifyUserPwdMap.put("newPassword2", newPwdAgain.getText().toString().trim());
        modifyUserPwdMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyUserPwdMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getModifyUserPwd(modifyUserPwdMap, true, false);
    }

    @Override
    public void resultModifyUserPwd(ModifyUserPwdBean data) {
        switch (data.getCode()) {
            case 200:
                finish();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
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
