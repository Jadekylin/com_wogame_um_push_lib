package com.madnow.umpush;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.wogame.util.AppInfoUtil;
import com.wogame.util.GMDebug;

/**
 *  如果用的是armeabi-v7a 那需要 将友盟提供的 armeabi 改成 armeabi-v7a
 */

public class UMPushService {
    private static UMPushService instance;
    private Activity mActivity;

    public static UMPushService getInstance() {
        if (instance == null) {
            instance = new UMPushService();
        }
        return instance;
    }

    public void initApplication(Context context){
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(context);
        mPushAgent.setResourcePackageName(AppInfoUtil.mPackageName);
        GMDebug.LogD(" UMPushService 注册成功：initApplication：-------->  ");
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                GMDebug.LogD(" UMPushService 注册成功：deviceToken：-------->  " + deviceToken);
            }
            @Override
            public void onFailure(String s, String s1) {
                GMDebug.LogD("UMPushService 注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }

    public void initActivity(Activity activity){
        mActivity = activity;

        PushAgent.getInstance(mActivity).onAppStart();
    }
}
