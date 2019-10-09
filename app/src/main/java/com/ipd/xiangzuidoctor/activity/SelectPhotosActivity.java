package com.ipd.xiangzuidoctor.activity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzuidoctor.R;
import com.ipd.xiangzuidoctor.adapter.SelectPhotosAdapter;
import com.ipd.xiangzuidoctor.base.BaseActivity;
import com.ipd.xiangzuidoctor.base.BasePresenter;
import com.ipd.xiangzuidoctor.base.BaseView;
import com.ipd.xiangzuidoctor.common.view.TopView;
import com.ipd.xiangzuidoctor.utils.ApplicationUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.ipd.xiangzuidoctor.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：多图查看
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/10/9.
 */
public class SelectPhotosActivity extends BaseActivity {

    @BindView(R.id.tv_select_photo)
    TopView tvSelectPhoto;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rv_select_photo)
    RecyclerView rvSelectPhoto;

    private List<String> imgList = new ArrayList<>();
    private SelectPhotosAdapter selectPhotosAdapter;
    private String imgUrl = "";//上传成功后返回的图片地址
    private List<LocalMedia> medias = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_photos;
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
        ImmersionBar.setTitleBar(this, tvSelectPhoto);

        tvTopTitle.setText(getIntent().getStringExtra("title"));
        imgUrl = getIntent().getStringExtra("imgUrl");

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(this, 4);
        rvSelectPhoto.setLayoutManager(NotUseList);
        rvSelectPhoto.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvSelectPhoto.setItemAnimator(new DefaultItemAnimator()); //默认动画

        String[] strs = imgUrl.split(",");
        for (int i = 0; i < strs.length; i++) {
            imgList.add(BASE_LOCAL_URL + strs[i]);
        }
        selectPhotosAdapter = new SelectPhotosAdapter(imgList);
        rvSelectPhoto.setAdapter(selectPhotosAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        selectPhotosAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                medias.clear();
                LocalMedia localMedia = new LocalMedia();
                localMedia.setCompressed(true);
                localMedia.setCompressPath(BASE_LOCAL_URL + imgUrl);
                medias.add(localMedia);
                PictureSelector.create(SelectPhotosActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(0, medias);
            }
        });
    }
}
