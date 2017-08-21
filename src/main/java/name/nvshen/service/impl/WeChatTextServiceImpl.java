package name.nvshen.service.impl;

import org.springframework.stereotype.Service;

import name.nvshen.service.WeChatTextService;

@Service
public class WeChatTextServiceImpl implements WeChatTextService {

    @Override
    public String getText(String content) {

        /*StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");
        buffer.append("1  天气预报").append("\n");
        buffer.append("2  公交查询").append("\n");
        buffer.append("3  周边搜索").append("\n");
        buffer.append("4  歌曲点播").append("\n");
        buffer.append("5  经典游戏").append("\n");
        buffer.append("6  美女电台").append("\n");
        buffer.append("7  人脸识别").append("\n");
        buffer.append("8  聊天唠嗑").append("\n\n");
        buffer.append("回复“?”显示此帮助菜单");
        return buffer.toString();*/
        
        if (content.contains("宝贝")) {
            return getLiusang();
        } else if (content.contains("天气")) {
            return getWeather();
        } else if (content.contains("百度")) {
            return getUrl();
        } else if (content.contains("买花")) {
            return getShop();
        } else {
            return "不知道你在说什么哦";
        }
        
    }
    
    public String getShop() {
        String url = "<a href=\"http://url.cn/4AmAnWA\">[鸿运当头]</a>";
        return url;
    }
    
    public String getUrl() {
        String url = "<a href=\"http://www.baidu.com\">百度一下</a>";
        return url;
    }
    
    public String getWeather() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("北京大雨一直下");
        return buffer.toString();
    }
    
    public String getLiusang() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是小七的私人秘书柳柳桑，请回复数字选择服务：").append("\n\n");
        buffer.append("1  拥抱小七").append("\n");
        buffer.append("2  亲吻小七").append("\n");
        buffer.append("3  陪小七逛街").append("\n");
        buffer.append("4  陪小七吃饭").append("\n");
        buffer.append("5  陪小七看电影").append("\n");
        buffer.append("回复“?”了解更多");
        return buffer.toString();
    }

}
