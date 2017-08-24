package name.nvshen.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import name.nvshen.util.HttpUtils;
 
 
/**
 * 获取token
 * @author David
 *
 */
@Component
public class WeChatTask {
    
    @Value("${wechat.grant_type}")
    private String grantType;
    @Value("${wechat.appid}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.token_url}")
    private String tokenUrl;
    
    
    /**
     * 服务启动时和定时获取token
     * @throws Exception
     */
    @PostConstruct
    public void getToken() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", grantType);
        params.put("appid", appId);
        params.put("secret", secret);
        /*String str = HttpUtils.sendGet(tokenUrl, params);
        String accessToken = JSONObject.parseObject(str).getString("access_token");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" token为："+accessToken);*/
    }
 
}