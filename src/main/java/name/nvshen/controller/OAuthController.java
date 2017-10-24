package name.nvshen.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import name.nvshen.pojo.OAuthConstant;
import name.nvshen.util.OAuthUtil;

/**
 * 微信网页授权
 * @author David
 *
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController {
	
    @RequestMapping(value="/login", method={RequestMethod.GET})
    public String index() {
        return "/login";
    }
    
    @RequestMapping(value="/welcome", method={RequestMethod.GET})
    public String welcome(ModelMap modelMap,
            @RequestParam("nickName") String nickName,
            @RequestParam("headImgUrl") String headImgUrl) {
        modelMap.put("nickName", nickName);
        modelMap.put("headImgUrl", headImgUrl);
        return "/welcome";
    }
    
    @RequestMapping(value="/auth", method={RequestMethod.GET, RequestMethod.POST}, produces="text/html;charset=UTF-8")
	public String login() throws UnsupportedEncodingException {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize"
                + "?appid=" + OAuthConstant.APPID
                + "&redirect_uri=" + URLEncoder.encode(OAuthConstant.BACKURL, "UTF-8")
                + "&response_type=code"
                + "&scope=" + OAuthConstant.SCOPE_GRANT
                + "&state=STATE#wechat_redirect";
	    
        return "redirect:"+url;
	}
    
    @RequestMapping(value="/callBack", method={RequestMethod.GET, RequestMethod.POST}, produces="text/html;charset=UTF-8")
    public String callBack(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
                + "?appid=" + OAuthConstant.APPID
                + "&secret=" + OAuthConstant.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";
        
        JSONObject jsonObj = OAuthUtil.doGetJson(url);
        String openid = jsonObj.getString("openid");
        String token = jsonObj.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo"
                + "?access_token=" + token
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject userInfo = OAuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);
        
        //使用微信用户信息直接登录，无需注册和绑定
        return "redirect:/oauth/welcome?nickName=" + URLEncoder.encode(userInfo.getString("nickname"), "UTF-8")
                + "&headImgUrl=" + userInfo.getString("headimgurl");
    }
}
