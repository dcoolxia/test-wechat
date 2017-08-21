package name.nvshen.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfigController {

    /**
     * 接收微信接口配置信息
     * 
     * @param signature
     *            微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp
     *            时间戳
     * @param nonce
     *            随机数
     * @param echostr
     *            随机字符串
     * 
     *            开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，
     *            请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
     *            1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密
     *            3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @return
     */
    @RequestMapping(value = "/", method = { RequestMethod.GET })
    @ResponseBody
    public String requestByGet(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {

        String token = "xiaoqi";

        ArrayList<String> list = new ArrayList<String>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);

        Collections.sort(list);

        String str = list.get(0) + list.get(1) + list.get(2);
        System.out.println(DigestUtils.sha1Hex(str));

        System.out.println(signature);

        return echostr;
    }

}
