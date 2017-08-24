package name.nvshen.common;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import name.nvshen.util.HttpUtils;

 
/**
 * 获取微信用户信息
 * @author David
 *
 */
@Component
public class WeChatUesrInfo {
    
    @Value("${wechat.user_url}")
    private String userUrl;
    
    /**
     * 通过openid获取用户微信信息
     * @param openId
     * @return nickname昵称 headimgurl头像 sex性别
     * @throws Exception
     */
    public HashMap<String, String> getUserInfo(String openId) throws Exception {
        
        String token = "esHaqHK3-GAO9_nCEt0Y55NPHAFJ1YWJUM0hK_qLyDa6XEX"
                + "wOxRfWDz-oaJSgaC8fOIm4uO666nd2lVonyWNqz06jBJmhAztoLZf1_WNRm95N4L14v1esV5JOk0tiR_uGDHfADAZWA";
        
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", token);
        params.put("openid", openId); 
        params.put("lang", "zh_CN");
        String str = HttpUtils.sendGet(userUrl, params);
        params.clear();
        //这里返回参数只取了昵称、头像、和性别
        params.put("nickname", JSONObject.parseObject(str).getString("nickname")); //昵称
        params.put("headimgurl", JSONObject.parseObject(str).getString("headimgurl")); //头像
        params.put("sex", JSONObject.parseObject(str).getString("sex")); //性别
        return params;
    }
    
 
}