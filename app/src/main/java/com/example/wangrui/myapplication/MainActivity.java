package com.example.wangrui.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import static android.text.TextUtils.isEmpty;


public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/APPANDH5.html");
        //webView.loadUrl("file:///android_asset/indexH5与原生交互.html");

        webView.addJavascriptInterface(new MyJavaScriptInterface(), "jsext");
    }

    public class MyJavaScriptInterface {

        /**
         * 获取用户信息
         */
        @JavascriptInterface
        public void getUser(String params) {
            Log.i("wangrui666","getUser params"+params);
            JSONObject json = toJSON(params);
            if (json == null) {
                return;
            }

            String funcKey = json.getString("funcKey");
            JSONObject user = new JSONObject();
            try {
                user.put("userId", "1e2322f");
                user.put("nickName", "昵称");
                user.put("phone", "手机号");
                user.put("photo", "头像地址");
                user.put("sex", "性别");
                user.put("oauthId", "35A729A687A479FFC18672BEF41BF9FC");
                JSONObject consigneeObj = new JSONObject();
                consigneeObj.put("consignee", "这是consignee");
                consigneeObj.put("phone", "这是phone");
                consigneeObj.put("address", "地址");
                user.put("consigneeInfo", consigneeObj);
            } catch (Exception e) {

            }

            String data = user.toJSONString();
            loadJS(funcKey + "(" + data + ")");
        }

        @JavascriptInterface
        public void goAddress(String params) {
            JSONObject json = toJSON(params);
            if (json == null) {
                return;
            }
            String id = json.getString("userId");
            Log.i("wangrui666","goAddress"+params);

        }

        /**
         * 开始截获声音分贝数
         */
        @JavascriptInterface
        public void startVolumeListener(String params) {
            //audioRecordUtils.start();

            JSONObject json = toJSON(params);
            if (json == null)
                return;

            String funcKey = json.getString("funcKey");
            if (isEmpty(funcKey))
                return;

            JSONObject voice = new JSONObject();
            voice.put("voiceValue","开始截获声音分贝数");
            String string = voice.toJSONString();

            loadJS(funcKey + "(" + string + ")");
        }

        /**
         * 停止截获声音分贝数
         */
        @JavascriptInterface
        public void stopVolumeListener(String params) {
            JSONObject json = toJSON(params);
            if (json == null)
                return;

            String funcKey = json.getString("funcKey");
            if (isEmpty(funcKey))
                return;

            JSONObject voice = new JSONObject();
            voice.put("voiceValue","停止截获声音分贝数");
            String s = voice.toJSONString();
            loadJS(funcKey + "(" + s + ")");
            //audioRecordUtils.stop();
        }

        /**
         * 秀赞验证用户
         *
         * @param params
         */
        @JavascriptInterface
        public void getUserThird(String params) {
            org.json.JSONObject json = toJSON2(params);
            if (json == null) {
                return;
            }
            String funcKey = json.optString("funcKey");
            String sign = json.optString("sign");
            final String client_id = json.optString("client_id");
            final String nonce = json.optString("nonce");


            loadJS(funcKey + "('"+"这里是秀赞回调的结果"+"')");

        }

        /**
         * 跳转登陆
         *
         */
        @JavascriptInterface
        public void goLogin(){
            Toast.makeText(MainActivity.this,"这里要跳转到登陆界面",Toast.LENGTH_SHORT).show();
        }

        /**
         *退出H5页面
         *
         */
        @JavascriptInterface
        public void backToApp () {
            Toast.makeText(MainActivity.this,"这里要退出H5页面",Toast.LENGTH_SHORT).show();
        }

        /**
         *新打开webview
         *
         */
        @JavascriptInterface
        public void openPage(String params) {
            Toast.makeText(MainActivity.this,"这里要新打开webview",Toast.LENGTH_SHORT).show();
        }

        /**
         *分享
         * @param params
         */
        @JavascriptInterface
        public void share(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            Log.i("wangrui666","share："+params);
        }

        /**
         * 根据link跳转页面
         * @param params
         */
        @JavascriptInterface
        public void openPageWithLink(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            Log.i("wangrui666","openPageWithLink："+params);
        }

        /**
         * 显示或隐藏返回按钮
         * @param
         */
        @JavascriptInterface
        public void showOrHiddenGoHomeBtn(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            Log.i("wangrui666","showOrHiddenGoHomeBtn："+params);
        }

        /**获取加密串方法
         *
         * @param params
         */
        @JavascriptInterface
        public void getEncryptionKey(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            String funcKey = json.getString("funcKey");
            JSONObject key = new JSONObject();
            key.put("api_sign","1e2322f");
            key.put("sign_time","昵称");
            String data = key.toJSONString();
            loadJS(funcKey + "(" + data + ")");
        }

        /**
         * 设置是否支持下拉刷新
         * @param params
         */
        @JavascriptInterface
        public void setRefreshEnabled(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            Log.i("wangrui666","setRefreshEnabled："+params);
        }

        /**
         * 是否拦截滑动事件
         *
         */
        @JavascriptInterface
        public void disPatchTouchViewPager(boolean params) {
            Log.i("wangrui666","disPatchTouchViewPager："+params);
        }

        /**
         * 自定义原生分享参数
         */

        @JavascriptInterface
        public void setShare(String params) {
            Log.i("wangrui666","setShare："+params);
        }

        /**
         * 跳转注册界面
         */
        @JavascriptInterface
        public void goRegister() {
            Toast.makeText(MainActivity.this,"这里应该跳转注册界面",Toast.LENGTH_SHORT).show();
        }

        /**
         *音频识别开始
         *
         */
        @JavascriptInterface
        public void startAudioRecognition(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            String funcKey = json.getString("funcKey");
            JSONObject audioRecognition = new JSONObject();
            audioRecognition.put("id","音频识别ID");
            String data = audioRecognition.toJSONString();
            loadJS(funcKey + "(" + data + ")");
        }

        /**
         * 音频识别停止
         */
        @JavascriptInterface
        public void stopAudioRecognition() {
            Toast.makeText(MainActivity.this,"这里应该停止音频识别",Toast.LENGTH_SHORT).show();
        }

        /**
         * 调起原生UGC上传页面
         *
         */
        @JavascriptInterface
        public void goUGC(String params) {
            Log.i("wangrui666","goUGC"+params);
        }


        @JavascriptInterface
        public void getAppinfo(String params) {
            JSONObject json = toJSON(params);
            if (json == null) return;
            String funcKey = json.getString("funcKey");
            JSONObject info = new JSONObject();
            info.put("info","这里是版本号");
            String data = info.toJSONString();
            loadJS(funcKey + "(" + data + ")");
        }
    }

    private void loadJS(final String js) {
        if (webView != null) {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    String str = "javascript:window." + js;
                    webView.loadUrl(str);
                    //Log.i(str);
                }
            });
        }

    }

    private JSONObject toJSON(String params) {
        try {
            if (params == null || params.trim().length() == 0) {
                Toast.makeText(this,"参数为空",Toast.LENGTH_SHORT).show();
                return null;
            }
            return JSON.parseObject(params);
        } catch (Exception e) {
            return null;
        }
    }

    private org.json.JSONObject toJSON2(String params) {
        try {
            if (params == null || params.trim().length() == 0) {
                //ToastUtil.showToast("参数为空");
                return null;
            }
            return new org.json.JSONObject(params);
        } catch (Exception e) {
            //LogUtils.e(e);
            return null;
        }
    }
}
