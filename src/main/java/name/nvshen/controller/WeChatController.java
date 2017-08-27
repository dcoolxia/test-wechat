package name.nvshen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import name.nvshen.common.WeChatUesrInfo;
import name.nvshen.message.response.Article;
import name.nvshen.message.response.NewsMessage;
import name.nvshen.message.response.TextMessage;
import name.nvshen.service.WeChatTextService;
import name.nvshen.util.MessageUtil;

/**
 * 微信请求
 * @author David
 *
 */
@Controller
public class WeChatController {

    @Autowired
    private WeChatTextService textService;
    @Autowired
    private WeChatUesrInfo weChatUesrInfo;
    
    @RequestMapping(value = "/", method = { RequestMethod.POST }, produces="text/html;charset=UTF-8")
    @ResponseBody
    public String requestByPost(HttpServletRequest request) {

        String respMessage = null;

        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //respContent = "您发送的是文本消息！";
                respContent = textService.getText(requestMap.get("Content"));
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    NewsMessage newsMessage = new NewsMessage();
                    newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);
                    
                    HashMap<String, String> userInfo = weChatUesrInfo.getUserInfo(fromUserName);
                    
                    Article article=new Article();
                    article.setDescription("爱的路上携手一起走！"); //图文消息的描述
                    article.setPicUrl(userInfo.get("headimgurl")); //图文消息图片地址
                    article.setTitle("亲爱的："+userInfo.get("nickname")+",么么哒！"); //图文消息标题
                    article.setUrl("https://www.baidu.com/s?wd=%E4%B8%BA%E4%BB%80%E4%B9%88%E9%82%A3%E4%B9%88%E5%96%9C%E6%AC%A2%E6%9F%B3%E6%9F%B3"
                            + "&rsv_spt=1&rsv_iqid=0xb7ce597900212ce2&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&"
                            + "tn=baiduhome_pg&rsv_enter=1&rsv_sug3=9&rsv_sug1=5&rsv_sug7=100"); //图文url链接
                    List<Article> list=new ArrayList<Article>();
                    list.add(article); //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                    newsMessage.setArticleCount(list.size());
                    newsMessage.setArticles(list);
                    return MessageUtil.newsMessageToXml(newsMessage);
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    respContent = requestMap.get("EventKey");
                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
