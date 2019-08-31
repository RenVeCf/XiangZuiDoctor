package com.ipd.xiangzuidoctor.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：头像
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class PhotoActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TopView tvHead;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.riv_head)
    RadiusImageView rivHead;

    @Override
    public int getLayoutId() {
        return R.layout.activity_head;
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
        ImmersionBar.setTitleBar(this, tvHead);

        tvTopTitle.setText(getIntent().getStringExtra("title"));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Glide.with(this)
                            .load(PictureSelector.obtainMultipleResult(data).get(0).getCompressPath())
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    rivHead.setImageDrawable(resource);
                                }
                            });
                    break;
            }
        }
    }

    @OnClick({R.id.riv_head, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_head:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.sb_confirm:
                setResult(RESULT_OK, new Intent().putExtra("head", 1));
                finish();
                break;
        }
    }
}
