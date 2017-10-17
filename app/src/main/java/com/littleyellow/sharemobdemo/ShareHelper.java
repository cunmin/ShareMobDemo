package com.littleyellow.sharemobdemo;

import android.app.Activity;
import android.content.Context;

import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Samuel on 2017/5/12.
 */

public class ShareHelper {
    public final static String QQ_NAME = QQ.NAME;
    public final static String QZONE_NAME = QZone.NAME;
    public final static String WECHAT_NAME = Wechat.NAME;
    public final static String WECHATMOMENTS_NAME = WechatMoments.NAME;
    public final static String SINAWEIBO_NAME = SinaWeibo.NAME;
    public static void init(Context context, String appkey,String appsecret) {
        MobSDK.init(context,appkey,appsecret);
//        ShareSDK.setPlatformDevInfo(QQ.NAME, BuildConfig.QQ);
//        ShareSDK.setPlatformDevInfo(QZone.NAME, BuildConfig.QZone);
//        ShareSDK.setPlatformDevInfo(Wechat.NAME, BuildConfig.Wechat);
//        ShareSDK.setPlatformDevInfo(WechatMoments.NAME, BuildConfig.WechatMoments);
    }

    public static Builder builder(String platformName) {
        return new Builder().setPlatformName(platformName);
    }

    public static class Builder {
        private String title;
        private String titleUrl;
        private String text;
        private String url;
        private String imageUrl;
        private String comment;
        private String site;
        private String siteUrl;
        private String platformName;
        private ShareResultCallback shareResultCallback;
        private OnShareListener onShareListener;

        public Builder() {

        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        public Builder setTitleUrl(String titleUrl) {
            this.titleUrl = titleUrl;
            return this;
        }

        // text是分享文本，所有平台都需要这个字段
        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        // url仅在微信（包括好友和朋友圈）中使用
        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        // site是分享此内容的网站名称，仅在QQ空间使用
        public Builder setSite(String site) {
            this.site = site;
            return this;
        }

        public Builder setPlatformName(String platformName) {
            this.platformName = platformName;
            return this;
        }

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        public Builder setSiteUrl(String siteUrl) {
            this.siteUrl = siteUrl;
            return this;
        }

        public Builder setShareResultCallback(ShareResultCallback callback) {
            shareResultCallback = callback;
            return this;
        }

        public Builder setOnShareListener(OnShareListener listener){
            onShareListener = listener;
            return this;
        }

        public void share(final Context context) {
//            Platform.ShareParams sp = null;
//            Platform platform;
//            if (platformName == null || platformName.trim().length() == 0)
//                throw new RuntimeException("还未设置分享平台");
//
//            if (platformName.equals(QQ_NAME)) {
//                sp = new QQ.ShareParams();
//            } else if (platformName.equals(QZONE_NAME)) {
//                sp = new QZone.ShareParams();
//            } else if (platformName.equals(WECHAT_NAME)) {
//                sp = new Wechat.ShareParams();
//            } else if (platformName.equals(WECHATMOMENTS_NAME)) {
//                sp = new WechatMoments.ShareParams();
//            }else if (platformName.equals(SINAWEIBO_NAME)) {
//                sp = new SinaWeibo.ShareParams();
//            }
//            platform = ShareSDK.getPlatform(platformName);
//            sp.setTitle(title);
//            sp.setTitleUrl(titleUrl); // 标题的超链接
//            sp.setText(text);
//            sp.setUrl(url);
//            sp.setImageUrl(imageUrl);
//            sp.setComment(comment);
//            sp.setSite(site);
//            sp.setSiteUrl(siteUrl);
//            sp.setShareType(Platform.SHARE_WEBPAGE);
//            // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
//            platform.setPlatformActionListener(new PlatformActionListener() {
//                public void onError(Platform arg0, int arg1, Throwable arg2) {
//                    //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
//                    if (shareListener != null) {
//                        shareListener.onError();
//                    }
//                }
//
//                public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
//                    //分享成功的回调
//                    if (shareListener != null) {
//                        shareListener.onComplete();
//                    }
//                }
//
//                public void onCancel(Platform arg0, int arg1) {
//                    //取消分享的回调
//                    if (shareListener != null) {
//                        shareListener.onCancel();
//                    }
//                }
//            });
//            // 执行图文分享
//            platform.share(sp);




            final OnekeyShare oks = new OnekeyShare();
            //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
            if (platformName != null) {
                oks.setPlatform(platformName);
            }
            //关闭sso授权
            oks.disableSSOWhenAuthorize();
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle(title);
            // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
            oks.setTitleUrl(titleUrl);
            // text是分享文本，所有平台都需要这个字段
            oks.setText(text);
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            oks.setImageUrl(imageUrl);
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl(url);
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment(comment);
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(site);
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl(siteUrl);
            oks.setSilent(true);
            oks.setCallback(new PlatformActionListener() {
                @Override
                public void onComplete(final Platform platform, final int i, final HashMap<String, Object> hashMap) {
                    if(null!=shareResultCallback){
                        try {
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shareResultCallback.onComplete(platform.getName(),i,hashMap);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            shareResultCallback.onComplete(platform.getName(),i,hashMap);
                        }
                    }
                }

                @Override
                public void onError(final Platform platform, final int i, final Throwable throwable) {
                    if(null!=shareResultCallback){
                        try {
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shareResultCallback.onError(platform.getName(),i,throwable);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            shareResultCallback.onError(platform.getName(),i,throwable);
                        }
                    }
                }

                @Override
                public void onCancel(final Platform platform, final int i) {
                    if(null!=shareResultCallback){
                        try {
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shareResultCallback.onCancel(platform.getName(),i);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            shareResultCallback.onCancel(platform.getName(),i);
                        }
                    }
                }
            });
            if(null!=onShareListener){
                onShareListener.onShare(platformName, oks, new ShareCallback() {
                    @Override
                    public void onStart() {
                        //启动分享
                        oks.show(context);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }else {
                //启动分享
                oks.show(context);
            }
        }
    }

    public interface ShareCallback {
        void onStart();
        void onCancel();
    }

    public interface OnShareListener {
        void onShare(String platform, Object oks,ShareCallback callback);
    }

    public interface ShareResultCallback {
        void onComplete(String var1, int var2, Object var3);

        void onError(String var1, int var2, Throwable var3);

        void onCancel(String var1, int var2);
    }
}