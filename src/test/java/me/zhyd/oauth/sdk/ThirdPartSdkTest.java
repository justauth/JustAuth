package me.zhyd.oauth.sdk;

import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.10.0
 */
public class ThirdPartSdkTest {

    @Test
    public void huawei() {
        String code = "CF1IwmFc6uZABI9Y795BkhXfvHidIFFw04I4Zc4KML4n+vlXxwNUcQKS4xlopjFDpEk6LzQbjwdTNxvjZ9jqnd/1m5nswhx8X7e0/dL2kyGAMVZWFgVq9ClxNN18b+Z0xtfJjkm7bDnfC3W5h4COgTCoLSjiWKSHWp5hCunp6pQRo1FHovZXm13TLNlhF9mCVtJx3kTQ";

        Map<String, String> form = new HashMap<>(5);
        form.put("grant_type", "authorization_code");
        form.put("code", code);
        form.put("client_id", "100994535");
        form.put("client_secret", "22aea400bef603fef26d15a79c806eb477b35de0a529758f2a3b1bda32bfb80d");
        form.put("redirect_uri", "http://127.0.0.1:8443/oauth/callback/huawei");

        String response = HttpUtil.post("https://oauth-login.cloud.huawei.com/oauth2/v2/token", form, false);
        System.out.println(response);

        // {"access_token":"accessToken","expires_in":3600,"refresh_token":"refreshToken","scope":"https:\/\/www.huawei.com\/auth\/account\/base.profile","token_type":"Bearer"}

        //
        form.clear();
        form.put("nsp_ts", System.currentTimeMillis() + "");
        form.put("access_token", JSONObject.parseObject(response).getString("access_token"));
        form.put("nsp_fmt", "JS");
        // form.put("nsp_cb", "_jqjsp");
        form.put("nsp_svc", "OpenUP.User.getInfo");
        String response2 = HttpUtil.post("https://api.vmall.com/rest.php", form, false);
        System.out.println(response2);
        // 华为性别 0是男，女是1
        // {"gender":1,"headPictureURL":"https://upfile-drcn.platform.hicloud.com/FileServer/image/b.0260086000226601572.20190415065228.iBKdTsqaNkdPXSz4N7pIRWAgeu45ec3k.1000.9A5467309F9284B267ECA33B59D3D7DA4A71BC732D3BB24EC6B880A73DEE9BAB.jpg","languageCode":"zh-CN","userID":"260086000226601572","userName":"151****2326","userState":1,"userValidStatus":1}
    }
}
