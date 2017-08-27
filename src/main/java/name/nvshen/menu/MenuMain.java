package name.nvshen.menu;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import name.nvshen.util.HttpUtils;

/**
 * 自定义菜单
 * @author David
 *
 */
public class MenuMain {
 
    public static void main(String[] args) {
        String accessToken = "WsOnYaP_E81N2Ylm-pWLP7fK2lgaFZPgH2llQYhTDN-4SJSlP"
                + "fq6jh-C9NpGMgiPcTdX1kxsTN7B5p_bwQGYiXjWUN6ydvpqO2QGTkfwWjMY_-yHNhLz2n49fIgTJRgbAJKcAIAFOP";
        
//        createMenu(accessToken);
        
        queryMenu(accessToken);
        
//        removeMenu(accessToken);
    }
    
    /**
     * 创建菜单
     * @param accessToken
     */
    public static void createMenu(String accessToken){
        //定义第一个视图型按钮
        ViewButton viewButton1 = new ViewButton();
        viewButton1.setName("百度");
        viewButton1.setUrl("https://m.baidu.com");
        
        //定义第二个点击型按钮
        ClickButton clickButton1 = new ClickButton();
        clickButton1.setName("文本");
        clickButton1.setKey("text");
        
        //定义第三个复杂型按钮
        ComplexButton complexButton1 = new ComplexButton();
        //复杂按钮中的视图型按钮
        ViewButton viewButton2 = new ViewButton();
        viewButton2.setName("新浪");
        viewButton2.setUrl("http://m.sina.com.cn");
        //复杂按钮中的点击型按钮
        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("图文");
        clickButton2.setKey("image");
        
        complexButton1.setName("菜单");
        complexButton1.setSub_button(new Button[] { viewButton2, clickButton2 });
        
        //定义菜单
        Menu menu = new Menu();
        menu.setButton(new Button[] { viewButton1, clickButton1, complexButton1 });
        
        // 将菜单对象转换成json字符串  
        String jsonMenu = JSONObject.toJSONString(menu);
        
        System.out.println(jsonMenu);
        
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        
        try{
            String result = HttpUtils.sendPostBuffer(url, jsonMenu);
            System.out.println(result);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
        
    }
    
    /**
     * 查询菜单
     * @param accessToken
     */
    public static void queryMenu(String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        try{
            String result = HttpUtils.sendGet(url, params);
            System.out.println(result);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
    }
    
    /**
     * 删除菜单
     * @param accessToken
     */
    public static void removeMenu(String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        try{
            String result = HttpUtils.sendGet(url, params);
            System.out.println(result);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
    }
 
}