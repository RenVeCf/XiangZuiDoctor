package com.ipd.xiangzuidoctor.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ipd.xiangzuidoctor.R;

import static com.xuexiang.xui.utils.ResUtils.getString;

/**
 * Description ：底部弹出Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public abstract class ShareDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private TextView tvWechat, tvMoments, tvQQ;
    private Button btCancel;

    public ShareDialog(Activity activity) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);

        tvWechat = (TextView) findViewById(R.id.tv_wechat);
        tvMoments = (TextView) findViewById(R.id.tv_moments);
        tvQQ = (TextView) findViewById(R.id.tv_qq);
        btCancel = (Button) findViewById(R.id.bt_cancel);

        tvWechat.setOnClickListener(this);
        tvMoments.setOnClickListener(this);
        tvQQ.setOnClickListener(this);
        btCancel.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_wechat:
                break;
            case R.id.tv_moments:
                break;
            case R.id.tv_qq:
                break;
            case R.id.bt_cancel:
                this.cancel();
                break;
        }
    }

//    // 分享微信好友
//    private void showWeChatShare(String url, String platform) {
//        OnekeyShare oks = new OnekeyShare();
//        if (platform != null) {
//            oks.setPlatform(platform);
//        }
//        oks.disableSSOWhenAuthorize();
//        oks.setTitle(getString(R.string.app_name));
//        oks.setText("每个人必备的手机软件，发布资讯，招商引流，扩展人脉，解决需求，商机无限。");
//        Bitmap bitmap = BitmapFactory.decodeResource(activity.getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
//        oks.setImageData(bitmap);//bitmap格式图片
//        oks.setUrl(url);
//        oks.show(this);
//    }
}
