package com.android.tonight8.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tonight8.R;
import com.android.tonight8.base.BaseActivity;
import com.android.tonight8.base.Tonight8App;
import com.android.tonight8.utils.Utils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Description:登录接口
 * @author:LiuZhao
 * @Date:2014年12月25日
 */

public class LoginActivity extends BaseActivity {

    /**
     * QQ登录
     */
    @ViewInject(R.id.btn_qq_login_ok)
    private Button btn_qq_login_ok;
    /**
     * 微信登录
     */
    @ViewInject(R.id.btn_wx_login_ok)
    private Button btn_wx_login_ok;
    /**
     * 用户注册
     */
    @ViewInject(R.id.btn_user_register)
    private Button btn_user_register;
    /**
     * 用户登录
     */
    @ViewInject(R.id.btn_user_login)
    private Button btn_user_login;

    private UserInfo mInfo;
    /**
     * 用户登录ID
     */
    @ViewInject(R.id.et_user_id)
    private EditText et_user_id;
    /**
     * 用户登录密码
     */
    @ViewInject(R.id.et_user_pwd)
    private EditText et_user_pwd;
    private TextView mUserInfo;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        mUserInfo.setVisibility(View.VISIBLE);
                        mUserInfo.setText(response.getString("nickname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (msg.what == 1) {
                // Bitmap bitmap = (Bitmap)msg.obj;
                // mUserLogo.setImageBitmap(bitmap);
                String imageurl = (String) msg.obj;
                // Tonight8App.getSelf().bitmapUtils.display(mUserLogo,
                // imageurl);
            }
        }

    };
    public IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref",
                    "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                Tonight8App.getSelf().mTencent.setAccessToken(token, expires);
                Tonight8App.getSelf().mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    public static boolean ready(Context context) {
        if (Tonight8App.getSelf().mTencent == null) {
            return false;
        }
        boolean ready = Tonight8App.getSelf().mTencent.isSessionValid()
                && Tonight8App.getSelf().mTencent.getQQToken().getOpenId() != null;
        if (!ready) {
            Toast.makeText(context, "login and get openId first, please!",
                    Toast.LENGTH_SHORT).show();
        }
        return ready;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        getActionBarBase("登录");

    }

    @OnClick({R.id.btn_wx_login_ok, R.id.btn_qq_login_ok})
    public void onClick(View arg0) {
        switch (arg0.getId()) {

            // case R.id.btn_sina_login_cancel:
            // // 注销按钮
            // new LogoutAPI(LoginActivity.this, Tonight8App.SINA_APP_KEY,
            // AccessTokenKeeper.readAccessToken(LoginActivity.this))
            // .logout(mLogoutListener);
            // break;
            case R.id.btn_wx_login_ok:
                weixinLogin();
                break;

            case R.id.btn_qq_login_ok:
                QqLogin();
                break;
            // case R.id.btn_qq_login_cancle:
            // Tonight8App.getSelf().mTencent.logout(LoginActivity.this);
            // break;

            default:
                break;
        }
    }

    /**
     * @return void 返回类型
     * @throws
     * @Title: weixinLogin微信登录
     * @Description: TODO
     */
    private void weixinLogin() {

        // 微信授权登录认证请求 oauth request
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        Tonight8App.getSelf().wxApi.sendReq(req);
    }

    private void updateUserInfo() {
        if (Tonight8App.getSelf().mTencent != null
                && Tonight8App.getSelf().mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    new Thread() {

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject) response;
                            if (json.has("figureurl")) {
                                Bitmap bitmap = null;
                                String imageurl = null;
                                try {
                                    // bitmap =
                                    // Util.getbitmap(json.getString("figureurl_qq_2"));
                                    imageurl = json.getString("figureurl_qq_2");
                                } catch (JSONException e) {

                                }
                                Message msg = new Message();
                                // msg.obj = bitmap;
                                msg.obj = imageurl;
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this,
                    Tonight8App.getSelf().mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {
            mUserInfo.setText("");
            mUserInfo.setVisibility(View.GONE);
        }
    }

    /**
     * @return void 返回类型
     * @throws
     * @Title: onClickLogin QQ登录
     */
    private void QqLogin() {
        if (!Tonight8App.getSelf().mTencent.isSessionValid()) {
            Tonight8App.getSelf().mTencent.login(this, "all", loginListener);
            Log.d("SDKQQAgentPref",
                    "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            Tonight8App.getSelf().mTencent.logout(this);
            updateUserInfo();
        }
    }

    /**
     * @author asus QQ登录返回
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                Utils.toast("返回为空,登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                Utils.toast("返回为空,登录失败");
                return;
            }
            Utils.toast("登录成功");
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Utils.toast("登录出错： " + e.errorDetail);
            Utils.dismissDialog();

        }

        @Override
        public void onCancel() {
            Utils.toast("登录取消： ");
            Utils.dismissDialog();
        }
    }

}
