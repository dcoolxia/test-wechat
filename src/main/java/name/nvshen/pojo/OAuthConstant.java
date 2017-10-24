package name.nvshen.pojo;

public class OAuthConstant {

    public static final String APPID = "wx71a94214415a75c2";
    public static final String APPSECRET = "ea212111631e37bc8be27966c799401d";
    public static final String BACKURL = "http://nvshen.vicp.io/test-wechat/oauth/callBack";
    /**
     * 静默授权，不弹出授权页面，直接跳转，只能获取用户openid
     */
    public static final String SCOPE_SILENCE = "snsapi_base";
    /**
     * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
     */
    public static final String SCOPE_GRANT = "snsapi_userinfo";
}
