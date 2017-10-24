package name.nvshen.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {

    public static void main(String[] args) {
        /**
         * jsapi_ticket=HoagFKDcsGMVCIY2vOjf9mQ4NgSgFEGYHAya_pAtkS6vuxqRDPXeGsosmL1hXgXteHBn_nrwgi7rSOyQgPwpyw&noncestr=Sdsjk7asSs&timestamp=1505892395&url=http://10.1.24.227:8302/giftVoucher/give?goCode=123
         * 56b98a55e431dc1a44219e20b3d1a4b1209daf3c
         */
        
        String jsapi_ticket = "jsapi_ticket="+"HoagFKDcsGMVCIY2vOjf9mQ4NgSgFEGYHAya_pAtkS6vuxqRDPXeGsosmL1hXgXteHBn_nrwgi7rSOyQgPwpyw";
        String noncestr = "noncestr="+"Sdsjk7asSs"; // 必填，生成签名的随机串
        String timestamp = "timestamp="+"1505892395";// 必填，生成签名的时间戳
        String url = "url="+"http://10.1.24.227:8302/giftVoucher/give?goCode=123";
        
        ArrayList<String> list = new ArrayList<String>();
        list.add(noncestr);
        list.add(jsapi_ticket);
        list.add(timestamp);
        list.add(url);
        Collections.sort(list);
        
        String string1 = list.get(0) + "&" + list.get(1) + "&" + list.get(2) + "&" + list.get(3);
        System.out.println(string1);
        
        String signature = DigestUtils.sha1Hex(string1);
        System.out.println(signature);

    }

}
