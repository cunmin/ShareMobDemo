package com.littleyellow.sharemobdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.littleyellow.sharemobdemo.ShareHelper.WECHAT_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShareHelper.init(this,"219af729a21ea","e59e5e0b6fdb4b698c9e347d838636a5");
        Button shareWechat = (Button) findViewById(R.id.share_wechat);
        shareWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://item.btime.com/348qcopu9v99j6pss8r76jd0ach?from=haoindex";
                ShareHelper.builder(WECHAT_NAME)
                        .setComment("呵呵哒~~~")
                        .setImageUrl("https://p1.ssl.cdn.btime.com/t0125430fc3e2449144.jpg?size=550x366")
                        .setTitle("\"红通一号\"杨秀珠获刑8年 曾称死也要死在美国")
                        .setSite("\"红通一号\"杨秀珠获刑8年 曾称死也要死在美国")
                        .setUrl(url)
                        .setText("2017年10月13日，浙江省杭州市中级人民法院公开宣判“百名红通”1号人员杨秀珠贪污、受贿案，对被告人杨秀珠以贪污罪判处有期徒刑六年，并处罚金人民币五十万元；以受贿罪判处有期徒刑五年，并处罚金人民币三十万元；决定执行有期徒刑八年，并处罚金人民币八十万元；追缴杨秀珠贪污、受贿所得人民币二千六百三十九万九千四百五十五元。")
                        .setTitleUrl(url)
                        .setSite(url)
                        .setOnShareListener(new ShareHelper.OnShareListener() {
                            @Override
                            public void onShare(String platform, Object oks, ShareHelper.ShareCallback callback) {
//                                oks.setSilent(false);
                                callback.onStart();
                            }
                        })
                        .setShareResultCallback(new ShareHelper.ShareResultCallback() {
                            @Override
                            public void onComplete(String platform, int i, Object hashMap) {
                                toast("分享成功！");
                            }

                            @Override
                            public void onError(String platform, int i, Throwable throwable) {
                                toast("分享出错！");
                            }

                            @Override
                            public void onCancel(String platform, int i) {
                                toast("分享取消！");
                            }
                        })
                        .share(MainActivity.this);
            }
        });

        Button shareWechatMoments = (Button) findViewById(R.id.share_wechatMoments);
        shareWechatMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void toast(String msg){
        String name = Thread.currentThread().getName();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
